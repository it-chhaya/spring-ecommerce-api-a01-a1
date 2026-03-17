package co.istad.chhaya.webmvc.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductResponse(
        String code,
        String name,
        BigDecimal price,
        Boolean status,
        String categoryName
) {
}
