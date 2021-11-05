package com.sarpio.productservice.rest.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
public class ProductDAO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Unique identifier of the Product.",
            example = "1", required = true)
    private Long id;
    @Size(min = 3, max = 50)
    @NotBlank(message = "Name is mandatory")
    @Schema(description = "Product Name", example = "Product 001", required = true)
    private String name;
    @Size(min = 3, max = 50)
    @NotBlank(message = "Model is mandatory")
    @Schema(description = "Product Model", example = "Model 001", required = true)
    private String model;
    @Size(min = 3, max = 255)
    @NotBlank(message = "Description is mandatory")
    @Schema(description = "Product description", example = "Long text here up to 255 characters", required = true)
    private String description;
    @NotNull(message = "price cannot be null")
    @NotEmpty(message = "price cannot be empty")
    @Positive(message = "Price must have positive value")
    @Min(0)
    @Schema(description = "Product price, numeric value only", example = "100.33", required = true)
    private Double price;
    @NotNull
    @NotEmpty
    @Schema(description = "Unique identifier of the Category.", example = "1", required = true)
    private Long category_id;
}
