package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.OrderDao;
import com.example.demo.entity.Order;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int insertOrder(Order order) {
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("menuId", order.getMenuId());
    	param.addValue("userId", order.getUserId());
    	param.addValue("brownFlag", order.getBrownFlag());
    	param.addValue("bigFlag", order.getBigFlag());
    	param.addValue("riceIncFlag", order.getRiceIncFlag());
    	param.addValue("orderDate", order.getOrderDate());
    	return jdbcTemplate.update("INSERT INTO orders  (menu_id, user_id, brown_flag, big_flag, rice_inc_flag, order_date) VALUES (:menuId, :userId, :brownFlag, :bigFlag, :riceIncFlag, :orderDate)", param);
    }
    
    @Override
	public List<Order> findTodayOrder() {
    	List<Order> orderList =  jdbcTemplate.query("SELECT menu_id, om.name AS menu_name, user_id, u.name AS user_name, brown_flag, big_flag, rice_inc_flag, price, order_date FROM (SELECT menu_id, name, user_id, brown_flag, big_flag, rice_inc_flag, price, order_date FROM orders JOIN menu AS m ON menu_id = m.id WHERE to_char(order_date, 'YYYY-mm=dd') = to_char(now(), 'YYYY-mm=dd')) AS om JOIN users AS u ON user_id = u.id", new BeanPropertyRowMapper<Order>(Order.class));
    	for (Order order: orderList) {
    		order.setPrice(order.getPrice() + 0 * order.getBrownFlag() + 120 * order.getBigFlag() + 50 * order.getRiceIncFlag());
    	}
    	return orderList;
	}
    
    @Override
    public List<Order> findTodayOrderByUser(int id) {
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", id);
		List<Order> orderList = jdbcTemplate.query("SELECT menu_id, om.name AS menu_name, user_id, u.name AS user_name, brown_flag, big_flag, rice_inc_flag, price, order_date FROM (SELECT menu_id, name, user_id, brown_flag, big_flag, rice_inc_flag, price, order_date FROM orders JOIN menu AS m ON menu_id = m.id WHERE to_char(order_date, 'YYYY-mm=dd') = to_char(now(), 'YYYY-mm=dd')) AS om JOIN users AS u ON user_id = u.id WHERE user_id = :userId", param, new BeanPropertyRowMapper<Order>(Order.class));
		for (Order order: orderList) {
    		order.setPrice(order.getPrice() + 0 * order.getBrownFlag() + 120 * order.getBigFlag() + 50 * order.getRiceIncFlag());
    	}
    	return orderList;
    }
    
    @Override
    public int deleteTodayOrder(int id) {
    	MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		return jdbcTemplate.update("DELETE FROM orders WHERE user_id = :id", param);
    }
    
}
