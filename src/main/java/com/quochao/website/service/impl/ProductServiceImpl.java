package com.quochao.website.service.impl;

import com.cloudinary.Cloudinary;
import com.quochao.website.dto.CreateProductDto;
import com.quochao.website.dto.ProductDetailDto;
import com.quochao.website.dto.ProductDto;
import com.quochao.website.dto.ProductImagesDto;
import com.quochao.website.entity.Image;
import com.quochao.website.entity.Product;
import com.quochao.website.mapper.ProductMapper;
import com.quochao.website.repository.ProductRepository;
import com.quochao.website.service.ImageService;
import com.quochao.website.service.ProductColorService;
import com.quochao.website.service.ProductService;
import com.quochao.website.service.ProductSizeService;
import com.quochao.website.util.FileStorage;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductColorService productColorService;
    private final ProductSizeService productSizeService;
    private final Cloudinary cloudinary;

    private final ImageService imageService;

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(ProductMapper.getINSTANCE()::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findByCode(String code) {
        return ProductMapper.getINSTANCE().convertToDto(productRepository.findProductByCode(code));
    }

    @Override
    public Page<ProductDto> findAll(Integer page, Integer size, String field, String dir) {
        Page<Product> pageEntity = dir.equals("desc") ?
                productRepository.findAll(PageRequest.of(page, size, Sort.by(field).descending())) :
                productRepository.findAll(PageRequest.of(page, size, Sort.by(field).ascending()));
        return ProductMapper.getINSTANCE().convertPageEntityToDto(pageEntity);
    }

    @Override
    public List<ProductDto> searchByKeyword(String keyword) {
        return productRepository.searchProductByKeyword(keyword.trim().toLowerCase().replaceAll(" ", "-")).stream()
                .map(ProductMapper.getINSTANCE()::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductDto> findAllByCategory(String categoryCode, Integer page, Integer size, String field, String dir) {
        if (categoryCode == null || categoryCode.isEmpty())
            throw new IllegalStateException("Category code invalid");

        Page<Product> products = (dir.equals("asc")) ?
                productRepository.findAllByCategoryCode(categoryCode, PageRequest.of(page, size, Sort.by(field).ascending())) :
                productRepository.findAllByCategoryCode(categoryCode, PageRequest.of(page, size, Sort.by(field).descending()));

//        if category code without db to throw not found exception
        if (products.isEmpty())
            throw new IllegalStateException("Not found category");

        return ProductMapper.getINSTANCE().convertPageEntityToDto(products);
    }

    @Override
    public Page<ProductDto> findAllByBrand(String brandCode, Integer page, Integer size, String field, String dir) {
        if (brandCode == null || brandCode.isEmpty())
            throw new IllegalStateException("Brand brandCode invalid");

        Page<Product> products = (dir.equals("asc")) ?
                productRepository.findAllByBrandCode(brandCode, PageRequest.of(page, size, Sort.by(field).ascending())) :
                productRepository.findAllByBrandCode(brandCode, PageRequest.of(page, size, Sort.by(field).descending()));

//        if brand brandCode without db to throw not found exception
        if (products.isEmpty())
            throw new IllegalStateException("Not found brand");

        return ProductMapper.getINSTANCE().convertPageEntityToDto(products);
    }

    @Override
    public Page<ProductDto> findAllByColor(String code, Integer page, Integer size, String field, String dir) {
        if (code == null || code.isEmpty())
            throw new IllegalStateException("Brand brandCode invalid");

        Page<Product> products = (dir.equals("asc")) ?
                productRepository.findAllByColorCode(code, PageRequest.of(page, size, Sort.by(field).ascending())) :
                productRepository.findAllByColorCode(code, PageRequest.of(page, size, Sort.by(field).descending()));

        if (products.isEmpty())
            throw new IllegalStateException("Not found color");

        return ProductMapper.getINSTANCE().convertPageEntityToDto(products);
    }


    @Override
    public Page<ProductDto> filter(String brandCode, String categoryCode,
                                   String productSize, String productColor,
                                   Double minPrice, Double maxPrice,
                                   Integer page, Integer size,
                                   String field, String dir) {

        return null;
    }

    @Override
    public List<ProductDto> filter(String brand, Map<String, String> map) {
        String category = map.get("categoryCode");
        String productSize = map.get("product-size");
        String productColor = map.get("product-color");
        String minPrice = map.get("min");
        String maxPrice = map.get("max");
        String page = map.get("page");
        String size = map.get("size");
        String field = map.get("field");
        String dir = map.get("dir");
        return null;
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
    public Product save(CreateProductDto createProductDto) {
        if (createProductDto == null) throw new IllegalStateException("Product null");

        if (productRepository.findProductByName(createProductDto.getName()).isPresent())
            throw new IllegalStateException("Product was existed");

        Product product = ProductMapper.getINSTANCE().createProductToEntity(createProductDto);

        MultipartFile file = product.getFile();
        String imageUrl = "no-image";
        FileStorage fileStorage = new FileStorage(cloudinary, "product");
        if (file != null && !file.isEmpty()) {
            imageUrl = fileStorage.saveFile(file, product.getCode());
        }

        product.setImage(imageUrl);
        productRepository.save(product);
        product.setProductSizes(productSizeService.addAll(product, createProductDto.getSizes()));
        product.setProductColors(productColorService.addAll(product, createProductDto.getColors()));
        return product;
    }

    @Override
    public Product updateProduct(CreateProductDto createProductDto) {
        if (createProductDto == null || createProductDto.getId() == null) throw new IllegalStateException("NULL");
        Optional<Product> optionalProduct = productRepository.findById(createProductDto.getId());
        if (!optionalProduct.isPresent()) throw new IllegalStateException("Not found product");

        Product updated = optionalProduct.get();

        updated.setName(createProductDto.getName());
        updated.setCode(createProductDto.getName().trim().toLowerCase().replaceAll(" ", "-"));
        updated.setPrice(createProductDto.getPrice());
        updated.setCompetitive(createProductDto.getCompetitive());
        updated.setShortDescription(createProductDto.getShortDescription());
        updated.setDescription(createProductDto.getDescription());
        updated.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        MultipartFile file = createProductDto.getFile();
        if (file != null && !file.isEmpty()) {
            FileStorage fileStorage = new FileStorage(cloudinary, "product");
            String filename = fileStorage.saveFile(file, updated.getCode());
            updated.setImage(filename);
        }
        updated.setProductColors(productColorService.updateAll(updated, createProductDto.getColors()));
        updated.setProductSizes(productSizeService.updateAll(updated, createProductDto.getSizes()));
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
    public List<Image> saveImages(ProductImagesDto productImagesDto) {
        Optional<Product> optionalProduct = productRepository.findById(productImagesDto.getProductId());
        if (!optionalProduct.isPresent()) throw new IllegalStateException("Not found product");
        Product product = optionalProduct.get();
        int size = product.getImages().size();
        List<Image> result = new ArrayList<>();
        if (productImagesDto.getFiles().isEmpty()) return result;
        FileStorage fileStorage = new FileStorage(cloudinary, "product");

        for (MultipartFile file : productImagesDto.getFiles()
        ) {
            Image image = new Image();
            image.setProduct(product);
            if (file != null && !file.isEmpty()) {
                image.setImage(fileStorage.saveFile(file, product.getCode() + "-image-" + (size++)));
            } else image.setImage("no-image");
            result.add(imageService.save(image));
        }
        return result;
    }

    @Override
    public Image updateImages(Long id, MultipartFile file) {
        Image image = imageService.findById(id);
        if (image == null) throw new IllegalStateException("Not found image");

        FileStorage fileStorage = new FileStorage(cloudinary, "product");
        if (file != null && !file.isEmpty()) {
            image.setImage(fileStorage.saveFile(file, splitImageUrl(image.getImage())));
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
        fileStorage.removeFile(splitImageUrl(image.getImage()));
        return imageService.delete(id);
    }
}
