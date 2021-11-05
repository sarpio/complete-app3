package com.sarpio.orderservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive(message = "product_id must have positive value")
    @NotNull(message = "product_id cannot be null")
    private Long productId;
    @NotNull(message = "quantity cannot be null")
    private Integer quantity;
    private Double value;

    @NotNull(message = "item must be assigned to specific order")
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Orders orders;
}
