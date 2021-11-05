package com.sarpio.orderservice.controller.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class ItemsDAO {


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotNull(message = "product_id cannot be null")
    private Long productId;
    @NotNull(message = "quantity cannot be null")
    private Integer quantity;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double value;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long orderId;
}
