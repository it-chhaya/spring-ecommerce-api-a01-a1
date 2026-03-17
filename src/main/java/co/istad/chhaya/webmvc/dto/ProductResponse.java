package co.istad.chhaya.webmvc.dto;

import java.math.BigDecimal;

public record ProductResponse(
        String code,
        String name,
        BigDecimal price
) {
}
