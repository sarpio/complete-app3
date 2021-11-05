package com.sarpio.orderservice.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderCode;
    private Long customerId;
    private LocalDate createDate = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private Status status;
    private Double totalValue;
    private LocalDate lastUpdate;
    @OneToMany(mappedBy = "orders", cascade = CascadeType.REMOVE)
    private Set<Items> items;
}
