package co.istad.chhaya.webmvc.service;

import co.istad.chhaya.webmvc.dto.CreateProductRequest;
import co.istad.chhaya.webmvc.dto.ProductResponse;
import co.istad.chhaya.webmvc.dto.UpdateProductRequest;
import org.springframework.data.domain.Page;

public interface ProductService {


    ProductResponse patchProductByCode(String code, UpdateProductRequest updateProductRequest);


    ProductResponse getProductByCode(String code);


    Page<ProductResponse> getProducts(int pageNumber, int pageSize);


    ProductResponse createNewProduct(CreateProductRequest createProductRequest);

}
