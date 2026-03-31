package co.istad.chhaya.webmvc.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdateProductRequest(

        @Size(min = 1, max = 100)
        String name,

        @Positive
        @Max(10000)
        BigDecimal price,

        Boolean status,

        @Positive
        @Max(999999)
        Integer categoryId
) {
}
