package com.sarpio.productservice.service;

import com.sarpio.productservice.model.Products;
import com.sarpio.productservice.repository.ProductRepository;
import com.sarpio.productservice.rest.dao.ProductDAO;
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
public class ProductsService {

    private final ProductRepository productRepository;

    public List<ProductDAO> findAllProducts() {
        return productRepository.findAll().stream().map(EntityDaoMapper::map).collect(Collectors.toList());
    }

    public ProductDAO findProductById(Long id) {
        return EntityDaoMapper.map(productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found ...")));
    }

    public ProductDAO createNewProduct(ProductDAO dao) {
        Products entity = EntityDaoMapper.map(dao);
        productRepository.save(entity);
        return EntityDaoMapper.map(entity);
    }

    public String deleteProductById(Long id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return "Product with id: " + id + " deleted";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
