package com.quochao.website.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.quochao.website.dto.CreateProductDto;
import com.quochao.website.dto.FieldsDto;
import com.quochao.website.dto.ProductDetailDto;
import com.quochao.website.dto.ProductImagesDto;
import com.quochao.website.entity.*;
import com.quochao.website.mapper.ProductMapper;
import com.quochao.website.repository.*;
import com.quochao.website.service.ImageService;
import com.quochao.website.service.ProductColorService;
import com.quochao.website.service.ProductService;
import com.quochao.website.service.ProductSizeService;
import com.quochao.website.util.FileStorage;
import lombok.Data;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductColorService productColorService;
    private final ProductSizeService productSizeService;

    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final ReviewRepository reviewRepository;
    private final Cloudinary cloudinary;

    private final ImageService imageService;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }


    @Override
    public Page<Product> findAllByState(Integer page, Integer size, String field, String dir, boolean state) {
        return dir.equals("desc") ?
                productRepository.findAllByState(PageRequest.of(page, size, Sort.by(field).descending()), state) :
                productRepository.findAllByState(PageRequest.of(page, size, Sort.by(field).ascending()), state);
    }

    @Override
    public Product findByCode(String code) {
        Optional<Product> optional = productRepository.findProductByCode(code);
        return optional.orElseThrow(() -> new IllegalStateException("Not found product"));
    }

    @Override
    public Page<Product> findAll(Integer page, Integer size, String field, String dir) {
        return dir.equals("desc") ?
                productRepository.findAll(PageRequest.of(page, size, Sort.by(field).descending())) :
                productRepository.findAll(PageRequest.of(page, size, Sort.by(field).ascending()));
    }

    @Override
    public List<Product> searchByKeyword(String keyword) {
        return productRepository.searchProductByKeyword(keyword.trim().toLowerCase().replaceAll(" ", "-"));
    }

    @Override
    public Page<Product> findAllByCategory(String categoryCode, Integer page, Integer size, String field, String dir) {
        if (categoryCode == null || categoryCode.isEmpty())
            throw new IllegalStateException("Category code invalid");

        Page<Product> products = (dir.equals("asc")) ?
                productRepository.findAllByCategoryCode(categoryCode, PageRequest.of(page, size, Sort.by(field).ascending())) :
                productRepository.findAllByCategoryCode(categoryCode, PageRequest.of(page, size, Sort.by(field).descending()));

//        if category code without db to throw not found exception
        if (products.isEmpty())
            throw new IllegalStateException("Not found category");

        return products;
    }

    @Override
    public Page<Product> findAllByBrand(String brandCode, Integer page, Integer size, String field, String dir) {
        if (brandCode == null || brandCode.isEmpty())
            throw new IllegalStateException("Brand brandCode invalid");

        Page<Product> products = (dir.equals("asc")) ?
                productRepository.findAllByBrandCode(brandCode, PageRequest.of(page, size, Sort.by(field).ascending())) :
                productRepository.findAllByBrandCode(brandCode, PageRequest.of(page, size, Sort.by(field).descending()));

//        if brand brandCode without db to throw not found exception
        if (products.isEmpty())
            throw new IllegalStateException("Not found brand");

        return products;
    }

    @Override
    public List<Product> findLatestProducts() {
        List<Product> products = productRepository.findAll(Sort.by("createdAt").descending());
        return products.size() >= 5 ? products.subList(0, 5) : products;
    }

    @Override
    public List<Product> findBestSellerProducts() {
        List<Product> products = productRepository.findBestSellerProducts();
        return products.size() >= 5 ? products.subList(0, 5) : products;
    }

    @Override
    public List<Product> findHotProducts() {
        List<Product> products = productRepository.findHotProducts();
        return products.size() > 5 ? products.subList(0,5) : products;
    }

    @Override
    public FieldsDto getAllFields() {
        FieldsDto dto = new FieldsDto();
        dto.setBrands(brandRepository.findAll());
        dto.setCategories(categoryRepository.findAll());
        dto.setColors(colorRepository.findAll());
        dto.setSizes(sizeRepository.findAll());
        return dto;
    }

    @Override
    public List<Review> getReviewsByCode(String code) {
        Optional<Product> product = productRepository.findProductByCode(code);
        if (product.isPresent())
            return reviewRepository.getAllByProduct(product.get());
        else throw new IllegalStateException("Not found product");
    }

    @Override
    public Page<Product> findAllByColor(String code, Integer page, Integer size, String field, String dir) {
        if (code == null || code.isEmpty())
            throw new IllegalStateException("Brand brandCode invalid");

        Page<Product> products = (dir.equals("asc")) ?
                productRepository.findAllByColorCode(code, PageRequest.of(page, size, Sort.by(field).ascending())) :
                productRepository.findAllByColorCode(code, PageRequest.of(page, size, Sort.by(field).descending()));

        if (products.isEmpty())
            throw new IllegalStateException("Not found color");

        return products;
    }


    @Override
    public Page<Product> filter(String brandCode, String categoryCode,
                                String productSize, String productColor,
                                Double minPrice, Double maxPrice,
                                Integer page, Integer size,
                                String field, String dir) {
        if (maxPrice == null) maxPrice = Double.MAX_VALUE;
        Pageable pageable = (dir.equalsIgnoreCase("asc")) ?
                PageRequest.of(page, size, Sort.by(field).ascending()) :
                PageRequest.of(page, size, Sort.by(field).descending());
        System.out.println(brandCode + " " + categoryCode + " " + productSize + " " + productColor);
        return productRepository.filter(brandCode, categoryCode, productSize, productColor, minPrice, maxPrice, pageable);
    }


    //Service for admin
    @Override
    public Page<Product> findAllProducts(Integer page, Integer size, String field, String dir) {
        return dir.equals("desc") ?
                productRepository.findAll(PageRequest.of(page, size, Sort.by(field).descending())) :
                productRepository.findAll(PageRequest.of(page, size, Sort.by(field).ascending()));

    }

    @Override
    public ProductDetailDto findById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent())
            return ProductMapper.getINSTANCE().convertToProductDetailDto(optionalProduct.get());
        else throw new IllegalStateException("Not found product");
    }

    @Override
    public Product save(CreateProductDto createProduct) {
        if (createProduct == null) throw new IllegalStateException("Product null");

        if (productRepository.findProductByName(createProduct.getName()).isPresent())
            throw new IllegalStateException("Product was existed");

        Product product = ProductMapper.getINSTANCE().createProductToEntity(createProduct);
        if (createProduct.getFile() != null) {
            FileStorage fileStorage = new FileStorage(cloudinary, "product");
            product.setImage(fileStorage.saveFile(createProduct.getFile(), product.getCode()));
        }else product.setImage("no-image");
        product.setState(true);
        product.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        productRepository.save(product);
        product.setProductSizes(productSizeService.addAll(product, createProduct.getSizes()));
        product.setProductColors(productColorService.addAll(product, createProduct.getColors()));
        return product;
    }

    @Override
    public Product updateProduct(CreateProductDto createProduct) {
        if (createProduct == null || createProduct.getId() == null) throw new IllegalStateException("NULL");
        Optional<Product> optionalProduct = productRepository.findById(createProduct.getId());
        if (!optionalProduct.isPresent()) throw new IllegalStateException("Not found product");
        Product updated = optionalProduct.get();
        Product existed = productRepository.getByName(createProduct.getName());
        if (existed != null && !updated.getName().equals(existed.getName()))
            throw new IllegalStateException("Product was existed");

        updated.setName(createProduct.getName());
        updated.setCode(createProduct.getName().trim().toLowerCase().replaceAll(" ", "-"));
        updated.setPrice(createProduct.getPrice());
        updated.setCompetitive(createProduct.getCompetitive());
        updated.setShortDescription(createProduct.getShortDescription());
        updated.setDescription(createProduct.getDescription());
        if (createProduct.getBrand() != null) updated.setBrand(createProduct.getBrand());
        if (createProduct.getCategory() != null) updated.setCategory(createProduct.getCategory());
        if (createProduct.getFile() != null) {
            FileStorage fileStorage = new FileStorage(cloudinary, "product");
            updated.setImage(fileStorage.saveFile(createProduct.getFile(), updated.getCode()));
        }
        updated.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        updated.setProductColors(productColorService.updateAll(updated, createProduct.getColors()));
        updated.setProductSizes(productSizeService.updateAll(updated, createProduct.getSizes()));
        return updated;
    }

    @Override
    public Product deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) throw new IllegalStateException("Not found product");
        Product product = optionalProduct.get();

        product.setState(false);
        product.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        return product;
    }

    @Override
    public Product enableProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) throw new IllegalStateException("Not found product");
        Product product = optionalProduct.get();
        if (product.getState()) return product;
        product.setState(true);
        product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return product;
    }

    @Override
    public List<Image> saveImages(ProductImagesDto productImagesDto) {
        Optional<Product> optionalProduct = productRepository.findById(productImagesDto.getProductId());
        if (!optionalProduct.isPresent()) throw new IllegalStateException("Not found product");
        Product product = optionalProduct.get();
        if (productImagesDto.getImages() != null) {
            List<Image> result = new ArrayList<>();
            FileStorage fileStorage = new FileStorage(cloudinary, "product");
            for (MultipartFile url : productImagesDto.getImages()
            ) {
                Image image = new Image();
                image.setProduct(product);
                image.setImage(fileStorage.saveFile(url, product.getCode() + product.getImages().size() + 1));
                result.add(imageService.save(image));
            }
            return result;
        }
        return null;
    }

    @Override
    public Image updateImages(Long id, MultipartFile imageUrl) {
        Image image = imageService.findById(id);
        if (image == null) throw new IllegalStateException("Not found image");
        String[] subUrls = image.getImage().split("/");
        String name = subUrls[subUrls.length-1].split("\\.")[0];
        if (imageUrl != null) {
            FileStorage fileStorage = new FileStorage(cloudinary, "product");
            image.setImage(fileStorage.saveFile(imageUrl, name));
        }
        return image;
    }

    private String splitImageUrl(String url) {
        int indexStart = url.lastIndexOf("/");
        int indexEnd = url.lastIndexOf(".");
        return url.substring(indexStart, indexEnd);
    }

    @Override
    public Boolean deleteImages(Long id) {
        Image image = imageService.findById(id);
        FileStorage fileStorage = new FileStorage(cloudinary, "product");
        try {
            cloudinary.uploader().destroy("image" + "/" + splitImageUrl(image.getImage()), ObjectUtils.asMap());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imageService.delete(id);
    }

    @Override
    public Integer enableAll() {
        return productRepository.enableAll();
    }

    @Override
    public int removeProduct(Long id) {
        productRepository.deleteById(id);
        return 1;
    }

    @Override
    public int changColor() {
        Color color = new Color();
        color.setId(3L);
        return productRepository.changeColor(color);
    }
}
