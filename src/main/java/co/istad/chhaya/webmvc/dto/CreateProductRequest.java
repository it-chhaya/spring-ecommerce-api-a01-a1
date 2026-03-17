package co.istad.chhaya.webmvc.dto;

import java.math.BigDecimal;

public record CreateProductRequest(
        String name,
        BigDecimal price,
        Integer categoryId
) {
}
