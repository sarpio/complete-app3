package com.sarpio.orderservice.service;

import com.sarpio.orderservice.controller.dao.OrdersDAO;
import com.sarpio.orderservice.model.Orders;
import com.sarpio.orderservice.model.Status;
import com.sarpio.orderservice.repository.OrdersRepository;
import com.sarpio.orderservice.utils.EntityDaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public List<OrdersDAO> findAllOrders() {
        return ordersRepository
                .findAll()
                .stream()
                .map(EntityDaoMapper::map)
                .collect(Collectors.toList());
    }

    public OrdersDAO findOrderById(Long id) {
        if (ordersRepository.existsById(id)) {
            Orders entity = ordersRepository
                    .findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Order not found"));
            return EntityDaoMapper.map(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public OrdersDAO saveOrder(OrdersDAO dao) {
        if (dao != null) {
            Orders entity = EntityDaoMapper.map(dao);
            entity.setCreateDate(LocalDate.now());
            entity.setLastUpdate(LocalDate.now());
            entity.setOrderCode(orderCodeGenerator());
            entity.setTotalValue(0.0);
            ordersRepository.save(entity);
            return EntityDaoMapper.map(entity);
        }
        throw new EntityNotFoundException("Order cannot be empty or null");
    }

    public String deleteOrderById(Long id) {
        if (ordersRepository.existsById(id)) {
            ordersRepository.deleteById(id);
            return "Order with id: " + id + " has been removed together with all order items";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with given id not found");
    }

    public OrdersDAO changeOrderStatus(Long id, Status status) {
        Orders orders = ordersRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found ..."));
        orders.setLastUpdate(LocalDate.now());
        orders.setStatus(status);

        return EntityDaoMapper.map(ordersRepository.save(orders));
    }

    public String orderCodeGenerator() {
        Random random = new Random();
        String prefix = "ORDER/";
        String sufix = "/";
        String now = LocalDate.now().toString();
        String id = String.format("%04d", random.nextInt(10000));
        return prefix+now+sufix+id;
    }
}
