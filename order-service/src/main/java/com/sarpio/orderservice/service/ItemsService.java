package com.sarpio.orderservice.service;

import com.sarpio.orderservice.controller.dao.ItemsDAO;
import com.sarpio.orderservice.controller.dao.ProductDAO;
import com.sarpio.orderservice.model.Items;
import com.sarpio.orderservice.model.Orders;
import com.sarpio.orderservice.repository.ItemsRepository;
import com.sarpio.orderservice.repository.OrdersRepository;
import com.sarpio.orderservice.utils.EntityDaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemsService {

    private final static String PRODUCT_SERVICE_URL = "http://localhost:8100/api/products/";
    private final RestTemplate restTemplate = new RestTemplate();
    private final ItemsRepository itemsRepository;
    private final OrdersRepository ordersRepository;

    public List<ItemsDAO> findItemsByOrderId(Long id) {
        List<Items> itemsByOrderId = itemsRepository.findByOrdersId(id);
        return itemsByOrderId.stream().map(EntityDaoMapper::map).collect(Collectors.toList());
    }

    public List<ItemsDAO> findItemsByOrderIdAndCustomerId(Long customerId, Long orderId) {
        List<Items> allItems = itemsRepository.findAll();
        return allItems
                .stream()
                .filter(items -> orderId == null || items.getOrders().getId().equals(orderId))
                .filter(items -> customerId == null || items.getOrders().getCustomerId().equals(customerId))
                .map(EntityDaoMapper::map)
                .collect(Collectors.toList());
    }

    public ItemsDAO addItemToOrder(Long orderId, ItemsDAO dao) {
        Orders order = ordersRepository.findById(orderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Items entity = EntityDaoMapper.map(dao);
        entity.setOrders(order);
        entity.setValue(getItemValue(dao.getProductId(), dao.getQuantity()));
        itemsRepository.save(entity);
        order.setTotalValue(totalOrderValue(orderId));
        ordersRepository.save(order);
        return EntityDaoMapper.map(entity);
    }

    public ItemsDAO removeItemByIdFromOrder(Long itemId) {
        ItemsDAO dao = EntityDaoMapper.map(itemsRepository.findById(itemId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found ...")));
        itemsRepository.deleteById(itemId);
        Orders orders = ordersRepository.findById(dao.getOrderId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found ..."));
        orders.setTotalValue(totalOrderValue(dao.getOrderId()));
        ordersRepository.save(orders);
        return dao;
    }

    private Double getItemValue(Long productId, Integer qty) {
        ProductDAO dao = restTemplate.getForObject(PRODUCT_SERVICE_URL + productId, ProductDAO.class);
        Double value = null;
        if (dao != null) {
            value = dao.getPrice() * qty;
        }
        return value;
    }

    private Double totalOrderValue(Long orderId) {
        return itemsRepository.findByOrdersId(orderId).stream().mapToDouble(Items::getValue).sum();
    }
}
