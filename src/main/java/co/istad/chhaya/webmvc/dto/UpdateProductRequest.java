package co.istad.chhaya.webmvc.dto;

import java.math.BigDecimal;

public record UpdateProductRequest(
        String name,
        BigDecimal price
) {
}
