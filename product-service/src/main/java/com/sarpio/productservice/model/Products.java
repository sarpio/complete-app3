package com.sarpio.productservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Getter
@Setter
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Model is mandatory")
    private String model;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @NotNull(message = "price cannot be null")
    @NotEmpty(message = "price cannot be empty")
    @Positive(message = "Price must have positive value")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
