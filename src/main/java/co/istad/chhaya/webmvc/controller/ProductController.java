package co.istad.chhaya.webmvc.controller;

import co.istad.chhaya.webmvc.dto.CreateProductRequest;
import co.istad.chhaya.webmvc.dto.ProductResponse;
import co.istad.chhaya.webmvc.dto.UpdateProductRequest;
import co.istad.chhaya.webmvc.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{code}")
    public ProductResponse getProductByCode(@PathVariable String code) {
        log.info("getProductByCode: {}", code);
        return productService.getProductByCode(code);
    }


    @GetMapping
    public Page<ProductResponse> getProducts(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "20") int pageSize
    ) {
        log.info("pageNumber: {}, pageSize: {}",
                pageNumber,
                pageSize);

        return productService.getProducts(pageNumber, pageSize);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductResponse createNewProduct(
            @Valid @RequestBody CreateProductRequest createProductRequest
            ) {
        log.info("createProductRequest: {}", createProductRequest);
        return productService.createNewProduct(createProductRequest);
    }


    @PutMapping("/{code}")
    public void updateProductByCode(
            @PathVariable String code,
            @RequestBody UpdateProductRequest updateProductRequest
    ) {
        log.info("updateProductByCode: {}", code);
        log.info("updateProductRequest: {}", updateProductRequest);
    }


    @PatchMapping("/{code}")
    public void patchProductByCode(
            @PathVariable String code,
            @RequestBody UpdateProductRequest updateProductRequest
    ) {
        log.info("patchProductByCode: {}", code);
        log.info("patchProductRequest: {}", updateProductRequest);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    public void deleteProductByCode(
            @PathVariable String code
    ) {
        log.info("code: {}", code);
    }

}
