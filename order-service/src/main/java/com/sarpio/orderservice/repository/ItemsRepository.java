package com.sarpio.orderservice.repository;

import com.sarpio.orderservice.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Long> {
    List<Items> findByOrdersId(Long id);
}
