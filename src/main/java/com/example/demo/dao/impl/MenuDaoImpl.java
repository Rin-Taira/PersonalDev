package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.MenuDao;
import com.example.demo.entity.Menu;

@Repository
public class MenuDaoImpl implements MenuDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public Menu findById(String id) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", Integer.parseInt(id));
		List<Menu> list = jdbcTemplate.query("SELECT mc.id AS id, menu_name, price, category_id, category_name, description, COUNT(r.id) AS review_amount, SUM(star) AS review_star_amount FROM (SELECT m.id as id, m.name as menu_name, price, category_id, c.name AS category_name, description FROM menu AS m INNER JOIN categories AS c ON category_id = c.id WHERE m.id = :id) AS mc LEFT JOIN reviews AS r ON mc.id = menu_id GROUP BY mc.id, mc.menu_name, mc.price, mc.category_id, mc.category_name, mc.description", param, new BeanPropertyRowMapper<Menu>(Menu.class));
//		List<Menu> list = jdbcTemplate.query("SELECT m.id as id, m.name as menu_name, price, category_id, c.name AS category_name, description, review_amount, review_star_amount FROM menu AS m INNER JOIN categories AS c ON category_id = c.id WHERE m.id = :id ORDER BY m.id", param, new BeanPropertyRowMapper<Menu>(Menu.class));
        return list.isEmpty() ? null : list.get(0);
	}
	
	@Override
	public List<Menu> findByCategory(int categoryId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("category_id", categoryId);
		return jdbcTemplate.query("SELECT mc.id AS id, menu_name, price, category_id, category_name, description, COUNT(r.id) AS review_amount, SUM(star) AS review_star_amount FROM (SELECT m.id as id, m.name as menu_name, price, category_id, c.name AS category_name, description FROM menu AS m INNER JOIN categories AS c ON category_id = c.id WHERE category_id = :category_id) AS mc LEFT JOIN reviews AS r ON mc.id = menu_id GROUP BY mc.id, mc.menu_name, mc.price, mc.category_id, mc.category_name, mc.description", param, new BeanPropertyRowMapper<Menu>(Menu.class));
		//return jdbcTemplate.query("SELECT m.id as id, m.name as menu_name, price, category_id, c.name AS category_name, description, review_amount, review_star_amount FROM menu AS m INNER JOIN categories AS c ON category_id = c.id WHERE category_id = :category_id ORDER BY m.id", param, new BeanPropertyRowMapper<Menu>(Menu.class));
		// https://loglog.xyz/programming/java/jdbctemplate_query_select で table名とEntityの結びつきについて学べるよ！！
	}
	

}