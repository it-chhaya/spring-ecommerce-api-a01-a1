package co.istad.chhaya.webmvc.service;

import co.istad.chhaya.webmvc.domain.Category;
import co.istad.chhaya.webmvc.dto.CreateProductRequest;
import co.istad.chhaya.webmvc.dto.ProductResponse;
import co.istad.chhaya.webmvc.repository.CategoryRepository;
import co.istad.chhaya.webmvc.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductResponse createNewProduct(CreateProductRequest createProductRequest) {

        // TODO: Write logic to create a new product
        // 1. Validate category ID (exist or not)
        Category category = categoryRepository
                .findById(createProductRequest.categoryId())
                .orElseThrow(() -> new RuntimeException("Category ID doesn't exist"));
        log.info("category: {}", category.getId());


        return null;
    }

}
