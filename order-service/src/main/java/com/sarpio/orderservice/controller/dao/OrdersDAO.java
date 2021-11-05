package com.sarpio.orderservice.controller.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sarpio.orderservice.model.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class OrdersDAO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String orderCode;
    @NotNull(message = "customer_id cannot be null")
    @Min(1)
    private Long customerId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate createDate;
    private Status status;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double totalValue;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate lastUpdate;

}
