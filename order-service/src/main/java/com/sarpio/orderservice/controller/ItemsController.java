package com.sarpio.orderservice.controller;

import com.sarpio.orderservice.controller.dao.ItemsDAO;
import com.sarpio.orderservice.service.ItemsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Items")
@Tag(name = "Order Items controller")
public class ItemsController {

    private final ItemsService itemsService;

    @GetMapping("/{id}")
    @Operation(summary = "Find all Items following to OrderID")
    public ResponseEntity<List<ItemsDAO>> findItemsByOrderId(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(itemsService.findItemsByOrderId(id));
    }

    @GetMapping
    @Operation(summary = "Find all Items following to CustomerID and OrderID")
    public ResponseEntity<List<ItemsDAO>> findByCustomerIdAndOrderId(@Valid @RequestParam(value = "customerId", required = false) Long customerId,
                                                                     @Valid @RequestParam(value = "orderId", required = false) Long orderId) {
        return ResponseEntity.ok(itemsService.findItemsByOrderIdAndCustomerId(customerId, orderId));
    }

    @PostMapping
    @Operation(summary = "Add new item to the Order following to OrderID")
    public ResponseEntity<ItemsDAO> addItemToOrder(@Valid @RequestParam Long orderId, @Valid @RequestBody ItemsDAO dao) {
        return ResponseEntity.ok(itemsService.addItemToOrder(orderId, dao));
    }

    @DeleteMapping("/{itemId}")
    @Operation(summary = "Remove Order item follwing to ItemID")
    public ResponseEntity<ItemsDAO> removeItemFromOrder(@Valid @PathVariable("itemId") Long itemId) {
        return ResponseEntity.ok(itemsService.removeItemByIdFromOrder(itemId));
    }
}
