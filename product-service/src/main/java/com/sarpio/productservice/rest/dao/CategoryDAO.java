package com.sarpio.productservice.rest.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder

public class CategoryDAO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Unique identifier of the Category.",
            example = "1", required = true)
    private Long id;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 1, max = 50, message = "string value")
    @Schema(description = "Category Name", example = "Cateogry 1", required = true)
    private String name;
}
