package com.sarpio.productservice.rest;

import com.sarpio.productservice.rest.dao.CategoryDAO;
import com.sarpio.productservice.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
@Tag(name = "Category", description = "Category Controller")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Find All Products' Categories")
    public ResponseEntity<List<CategoryDAO>> getAllCategories(){
        return ResponseEntity.ok(categoryService.showAllCategories());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Product Category by CategoryID")
    public ResponseEntity<CategoryDAO> getCategoryById(@Valid @PathVariable("id") Long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping
    @Operation(summary = "Add new Product Category")
    public ResponseEntity<CategoryDAO> createNewCategory(@Valid @RequestBody CategoryDAO dao) {
        return ResponseEntity.ok(categoryService.createNewCategory(dao));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Product Category by CategoryID")
    public ResponseEntity<String> deleteCategoryById(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.deleteCategoryById(id));
    }
}
