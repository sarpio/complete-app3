package com.sarpio.orderservice.controller;

import com.sarpio.orderservice.controller.dao.OrdersDAO;
import com.sarpio.orderservice.model.Status;
import com.sarpio.orderservice.service.OrdersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
@Tag(name = "Orders Controller")
public class OrdersController {

    private final OrdersService ordersService;

    @GetMapping
    @Operation(summary = "Show all orders")
    public ResponseEntity<List<OrdersDAO>> findAllOrders() {
        return ResponseEntity.ok(ordersService.findAllOrders());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Show all orders following to OrderID")
    public ResponseEntity<OrdersDAO> findOrderById(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(ordersService.findOrderById(id));
    }

    @PostMapping
    @Operation(summary = "Create new order")
    public ResponseEntity<OrdersDAO> saveNewOrder(@Validated @RequestBody OrdersDAO dao) {
        return ResponseEntity.ok(ordersService.saveOrder(dao));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order by ID")
    public ResponseEntity<String> deleteOrderById(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(ordersService.deleteOrderById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update order status following to OrderID")
     public ResponseEntity<OrdersDAO> updateOrderStatusByOrderId(@Valid @PathVariable("id") Long id, @Valid @RequestParam Status status) {
        return ResponseEntity.ok(ordersService.changeOrderStatus(id, status));
    }
}
