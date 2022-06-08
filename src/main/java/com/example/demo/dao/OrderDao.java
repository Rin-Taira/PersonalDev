package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Order;

public interface OrderDao {

    public int insertOrder(Order order);
    
    public List<Order> findTodayOrder();
    
    public List<Order> findTodayOrderByUser(int id);
    
    public int deleteTodayOrder(int id);
}
