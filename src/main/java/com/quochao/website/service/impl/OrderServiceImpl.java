package com.quochao.website.service.impl;

import com.quochao.website.dto.CartDto;
import com.quochao.website.dto.CartItemDto;
import com.quochao.website.dto.CustomerDto;
import com.quochao.website.entity.Order;
import com.quochao.website.entity.OrderDetail;
import com.quochao.website.entity.Product;
import com.quochao.website.entity.User;
import com.quochao.website.mapper.OrderMapper;
import com.quochao.website.repository.OrderDetailRepository;
import com.quochao.website.repository.OrderRepository;
import com.quochao.website.repository.ProductRepository;
import com.quochao.website.repository.UserRepository;
import com.quochao.website.service.OrderService;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.swing.plaf.IconUIResource;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Data
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderServiceImpl implements OrderService {
    private Map<String, CartItemDto> cart = new HashMap<>();

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    private final OrderDetailRepository orderDetailRepository;

    private final UserRepository userRepository;

    @Override
    public CartDto getCart() {
        CartDto cartDto = new CartDto();
        List<CartItemDto> items = new ArrayList<>(cart.values());
        Integer totalAmount = items.stream().mapToInt(CartItemDto::getQuantity).sum();
        Double totalPrice = items.stream().mapToDouble(CartItemDto::getTotalPrice).sum();
        cartDto.setItems(items);
        cartDto.setTotalAmount(totalAmount);
        cartDto.setTotalPrice(totalPrice);
        return cartDto;
    }

    @Override
    public CartItemDto addToCart(String code, Integer quantity) {
        Optional<Product> optional = productRepository.findByCodeAndState(code, true);
        if (!optional.isPresent()) throw new IllegalStateException("Not found product");

        CartItemDto item;
        if (cart.containsKey(code)) {
            item = cart.get(code);
            item.setQuantity(item.getQuantity() + quantity);
            item.setTotalPrice(item.getUnitPrice() * item.getQuantity());
        } else {
            item = new CartItemDto();
            Product product = optional.get();
            item.setProductCode(code);
            item.setProduct(product);
            item.setUnitPrice(product.getPrice());
            item.setQuantity(quantity);
            item.setTotalPrice(item.getUnitPrice() * quantity);
        }
        cart.put(code, item);
        return item;
    }

    @Override
    public CartItemDto updateQuantity(String code, Integer quantity) {
        if (cart.get(code) == null) throw new IllegalStateException("Item not found");
        Optional<Product> optional = productRepository.findByCodeAndState(code, true);
        if (!optional.isPresent()) throw new IllegalStateException("Not found product");
        CartItemDto item = cart.get(code);
        item.setQuantity(quantity);
        item.setTotalPrice(item.getUnitPrice() * quantity);
        cart.put(code, item);
        return item;
    }

    @Override
    public CartDto removeItem(String code) {
        if (cart.containsKey(code)) cart.remove(code);
        else throw new IllegalStateException("Not found item");
        return getCart();
    }

    @Override
    public Order checkout(CustomerDto customerDto) {
        Optional<User> user = userRepository.findById(customerDto.getUserId());
        if (!user.isPresent()) throw new IllegalStateException("NOT FOUND USER");

        customerDto.getCart().stream().forEach(item -> addToCart(item.getCode(), item.getQuantity()));
        if (cart.isEmpty()) return null;
        CartDto cartDto = getCart();
        Order order = OrderMapper.getINSTANCE().convertToOrder(customerDto, cartDto);
        order.setUser(user.get());
        orderRepository.save(order);
        List<OrderDetail> orderDetails = cartDto.getItems().stream().map(item -> {
            OrderDetail orderDetail = OrderMapper.getINSTANCE().convertToOrderDetail(item);
            orderDetail.setOrder(order);
            return orderDetail;
        }).collect(Collectors.toList());
        orderDetailRepository.saveAll(orderDetails);
        cart.clear();
        return order;
    }

    @Override
    public List<Order> getOrderHistory(Long userId) {
        Optional<User> optional = userRepository.findById(userId);
        if (!optional.isPresent()) throw new IllegalStateException("Unauthorized");
        return orderRepository.findAllByUser(optional.get());
    }

    @Override
    public Boolean removeOrderHistory(User user, Long id) {
        orderRepository.deleteByUserAndId(user, id);
        return true;
    }


    @Override
    public Page<Order> getAll(Integer page, Integer size) {
        return orderRepository.findAll(PageRequest.of(page, size, Sort.by("orderAt").ascending()));
    }

    @Override
    public Boolean checkOrder(Long id) {
        Optional<Order> optional = orderRepository.findById(id);
        if (!optional.isPresent() || optional.get().getDeletedAt() != null)
            throw new IllegalStateException("Not found order");
        Order order = optional.get();
        if (order.getState()) return true;
        order.setState(true);
        return true;
    }

    @Override
    public Boolean deleteOrder(Long id, String reason) {
        Optional<Order> optional = orderRepository.findById(id);
        if (!optional.isPresent()) throw new IllegalStateException("Not found order");
        Order order = optional.get();
        if (order.getState()) throw new IllegalStateException("Processed orders cannot be deleted");

        if (order.getDeletedAt() != null) {
            if (order.getDeletedReason() == null) order.setDeletedReason(reason);
        } else {
            order.setDeletedAt(new Timestamp(System.currentTimeMillis()));
            order.setDeletedReason(reason);
        }
        return true;
    }

    @Override
    public Order getOrderDetail(Long id) {
        Optional<Order> optional = orderRepository.findById(id);
        if (!optional.isPresent()) throw new IllegalStateException("Not found order");
        return optional.get();
    }
}
