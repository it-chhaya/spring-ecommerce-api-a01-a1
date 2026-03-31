package co.istad.chhaya.webmvc.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateProductRequest(
        @NotBlank(message = "Name is required")
        @Size(min = 1, max = 100)
        String name,

        @NotNull(message = "Price is required")
        @Positive
        @Max(10000)
        BigDecimal price,

        @NotNull(message = "Category ID is required")
        @Positive
        @Max(999999)
        Integer categoryId
) {
}
