package co.istad.chhaya.webmvc.service;

import co.istad.chhaya.webmvc.domain.Category;
import co.istad.chhaya.webmvc.domain.Product;
import co.istad.chhaya.webmvc.dto.CreateProductRequest;
import co.istad.chhaya.webmvc.dto.ProductResponse;
import co.istad.chhaya.webmvc.dto.UpdateProductRequest;
import co.istad.chhaya.webmvc.mapper.ProductMapper;
import co.istad.chhaya.webmvc.repository.CategoryRepository;
import co.istad.chhaya.webmvc.repository.ProductRepository;
import co.istad.chhaya.webmvc.util.GenerateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }


    @Override
    public ProductResponse patchProductByCode(String code, UpdateProductRequest updateProductRequest) {
        // TODO:
        Product foundProduct = productRepository.findById(code)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product code not found"
                ));

        if (updateProductRequest.name() != null)
            foundProduct.setName(updateProductRequest.name());

        if (updateProductRequest.price() != null)
            foundProduct.setPrice(updateProductRequest.price());

        if (updateProductRequest.status() != null)
            foundProduct.setStatus(updateProductRequest.status());

        if (updateProductRequest.categoryId() != null) {
            Category foundCategory = categoryRepository
                    .findById(updateProductRequest.categoryId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Category ID doesn't exist"
                    ));
            foundProduct.setCategory(foundCategory);
        }

        Product updatedProduct = productRepository.save(foundProduct);

        return productMapper.productToProductResponse(updatedProduct);
    }


    @Override
    public ProductResponse getProductByCode(String code) {
        // TODO: Select a product from database by code
        return productRepository.findById(code)
                .map(productMapper::productToProductResponse)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product code not found!"
                ));
    }


    @Override
    public Page<ProductResponse> getProducts(int pageNumber, int pageSize) {
        // TODO: Select product from database using pagination
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> productPage = productRepository.findAll(pageable);

        return productPage.map(productMapper::productToProductResponse);
    }


    @Override
    public ProductResponse createNewProduct(CreateProductRequest createProductRequest) {

        // TODO: Write logic to create a new product
        // 1. Validate category ID (exist or not)
        Category category = categoryRepository
                .findById(createProductRequest.categoryId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category ID doesn't exist"
                ));
        log.info("category: {}", category.getId());

        // 2. Transfer data from DTO to Entity
        Product product = new Product();
        product.setCategory(category);
        product.setName(createProductRequest.name());
        product.setPrice(createProductRequest.price());

        // 3. Set system data
        product.setStatus(true);
        product.setCode(GenerateUtil.generateProductCode());

        // 4. Save data into database
        product = productRepository.save(product);

        // 5. Transfer data from Entity to DTO
        return productMapper.productToProductResponse(product);
    }

}
