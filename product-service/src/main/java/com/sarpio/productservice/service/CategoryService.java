package com.sarpio.productservice.service;

import com.sarpio.productservice.model.Category;
import com.sarpio.productservice.repository.CategoryRepository;
import com.sarpio.productservice.rest.dao.CategoryDAO;
import com.sarpio.productservice.utils.EntityDaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDAO> showAllCategories() {
        return categoryRepository.findAll().stream().map(EntityDaoMapper::map).collect(Collectors.toList());
    }

    public CategoryDAO getCategoryById(Long id) {
        return categoryRepository.findById(id).map(EntityDaoMapper::map).orElseThrow(() -> new EntityNotFoundException("Provided category not found ..."));
    }

    public CategoryDAO createNewCategory(CategoryDAO dao) {
        Category entity = EntityDaoMapper.map(dao);
        categoryRepository.save(entity);
        return EntityDaoMapper.map(entity);
    }

    public String deleteCategoryById(Long id) {
        if (categoryRepository.findById(id).isPresent()) {
            categoryRepository.deleteById(id);
            return "Category with id:" + id + " deleted";
        } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
