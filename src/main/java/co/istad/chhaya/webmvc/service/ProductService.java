package co.istad.chhaya.webmvc.service;

import co.istad.chhaya.webmvc.dto.CreateProductRequest;
import co.istad.chhaya.webmvc.dto.ProductResponse;

public interface ProductService {

    ProductResponse createNewProduct(CreateProductRequest createProductRequest);

}
