package com.sarpio.productservice.rest;

import com.sarpio.productservice.rest.dao.ProductDAO;
import com.sarpio.productservice.service.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Tag(name = "Product", description = "Product Controller")
public class ProductController {

    private final ProductsService productsService;

    @GetMapping
    @Operation(summary = "Find all Products")
    public ResponseEntity<List<ProductDAO>> findAllProducts() {
        return ResponseEntity.ok(productsService.findAllProducts());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Product by ProductID")
    public ResponseEntity<ProductDAO> findProductById(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(productsService.findProductById(id));
    }

    @PostMapping
    @Operation(summary = "Add new Product")
    public ResponseEntity<ProductDAO> addNewProduct(@Valid @RequestBody ProductDAO dao) {
        return ResponseEntity.ok(productsService.createNewProduct(dao));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Product by ProductID")
    public ResponseEntity<String> deleteProductById(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(productsService.deleteProductById(id));
    }

}
