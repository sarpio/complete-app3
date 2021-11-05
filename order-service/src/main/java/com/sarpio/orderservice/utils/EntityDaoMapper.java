package com.sarpio.orderservice.utils;

import com.sarpio.orderservice.controller.dao.ItemsDAO;
import com.sarpio.orderservice.controller.dao.OrdersDAO;
import com.sarpio.orderservice.model.Items;
import com.sarpio.orderservice.model.Orders;
import org.springframework.beans.BeanUtils;

public class EntityDaoMapper {

    public static OrdersDAO map(Orders entity) {
        OrdersDAO dao = OrdersDAO.builder().build();
        BeanUtils.copyProperties(entity, dao);
        return dao;
    }

    public static Orders map(OrdersDAO dao) {
        Orders entity = Orders.builder().build();
        BeanUtils.copyProperties(dao, entity);
        return entity;
    }

    public static ItemsDAO map(Items entity) {
        ItemsDAO dao = ItemsDAO.builder().build();
        BeanUtils.copyProperties(entity, dao);
        dao.setOrderId(entity.getOrders().getId());
        return dao;
    }

    public static Items map(ItemsDAO dao) {
        Orders orders = Orders.builder().build();
        orders.setId(dao.getOrderId());
        Items entity = Items.builder().build();
        BeanUtils.copyProperties(dao, entity);
        entity.setOrders(orders);
        return entity;
    }
}
