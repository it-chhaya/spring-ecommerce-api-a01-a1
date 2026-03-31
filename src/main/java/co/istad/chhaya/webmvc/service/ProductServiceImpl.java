package co.istad.chhaya.webmvc.service;

import co.istad.chhaya.webmvc.domain.Category;
import co.istad.chhaya.webmvc.domain.Product;
import co.istad.chhaya.webmvc.dto.CreateProductRequest;
import co.istad.chhaya.webmvc.dto.ProductResponse;
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

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Page<ProductResponse> getProducts(int pageNumber, int pageSize) {
        // TODO: Select product from database using pagination
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> productPage = productRepository.findAll(pageable);

        return productPage.map(product -> ProductResponse.builder()
                .code(product.getCode())
                .name(product.getName())
                .price(product.getPrice())
                .status(product.getStatus())
                .categoryName(product.getCategory().getName())
                .build());
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
        return ProductResponse.builder()
                .code(product.getCode())
                .name(product.getName())
                .price(product.getPrice())
                .status(product.getStatus())
                .categoryName(product.getCategory().getName())
                .build();
    }

}
