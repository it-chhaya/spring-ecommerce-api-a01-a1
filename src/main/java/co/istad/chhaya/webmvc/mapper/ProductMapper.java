package co.istad.chhaya.webmvc.mapper;

import co.istad.chhaya.webmvc.domain.Product;
import co.istad.chhaya.webmvc.dto.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse productToProductResponse(Product product) {
        return ProductResponse.builder()
                .code(product.getCode())
                .name(product.getName())
                .price(product.getPrice())
                .status(product.getStatus())
                .categoryName(product.getCategory().getName())
                .build();
    }

}
