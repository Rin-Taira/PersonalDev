package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrderDao;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;

	@Override
    public int insertOrder (Order order) {
        return orderDao.insertOrder(order);
    }

	@Override
	public List<Order> findTodayOrder() {
		return orderDao.findTodayOrder();
	}
	
	@Override
	public List<Order> findTodayOrderByUser(int id) {
		return orderDao.findTodayOrderByUser(id);
	}
	
	@Override
	public int deleteTodayOrder(int id) {
		return orderDao.deleteTodayOrder(id);
	}
}

