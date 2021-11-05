package com.sarpio.productservice.utils;


import com.sarpio.productservice.model.Category;
import com.sarpio.productservice.model.Products;
import com.sarpio.productservice.rest.dao.CategoryDAO;
import com.sarpio.productservice.rest.dao.ProductDAO;
import org.springframework.beans.BeanUtils;

public class EntityDaoMapper {

    public static Category map(CategoryDAO dao) {
        Category category = Category.builder().build();
        BeanUtils.copyProperties(dao, category);
        return category;
    }

    public static CategoryDAO map(Category entity) {
        CategoryDAO categoryDAO = CategoryDAO.builder().build();
        BeanUtils.copyProperties(entity, categoryDAO);
        return categoryDAO;
    }

    public static Products map(ProductDAO dao) {
    Products entity = Products.builder().build();
    BeanUtils.copyProperties(dao, entity);
    Category category = Category.builder().id(dao.getCategory_id()).build();
    entity.setCategory(category);
    return entity;
    }

    public static ProductDAO map(Products entity) {
        ProductDAO dao = ProductDAO.builder().build();
        BeanUtils.copyProperties(entity, dao);
        dao.setCategory_id(entity.getCategory().getId());
        return dao;
    }
}
