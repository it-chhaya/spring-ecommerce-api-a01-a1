package co.istad.chhaya.webmvc.service;

import co.istad.chhaya.webmvc.dto.CreateProductRequest;
import co.istad.chhaya.webmvc.dto.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {

    ProductResponse getProductByCode(String code);


    Page<ProductResponse> getProducts(int pageNumber, int pageSize);


    ProductResponse createNewProduct(CreateProductRequest createProductRequest);

}
