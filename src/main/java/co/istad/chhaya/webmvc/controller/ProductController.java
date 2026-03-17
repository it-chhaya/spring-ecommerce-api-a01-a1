package co.istad.chhaya.webmvc.controller;

import co.istad.chhaya.webmvc.dto.CreateProductRequest;
import co.istad.chhaya.webmvc.dto.ProductResponse;
import co.istad.chhaya.webmvc.dto.UpdateProductRequest;
import co.istad.chhaya.webmvc.service.ProductService;
import lombok.extern.slf4j.Slf4j;
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
        return null;
    }


    @GetMapping
    public List<ProductResponse> getProducts(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "20") int pageSize,
            @RequestParam(required = false, defaultValue = "") String name
    ) {
        log.info("pageNumber: {}, pageSize: {}, name: {}",
                pageNumber,
                pageSize,
                name);

        return List.of();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductResponse createNewProduct(
            @RequestBody CreateProductRequest createProductRequest
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
