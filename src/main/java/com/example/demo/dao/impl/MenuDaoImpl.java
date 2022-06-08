package com.example.demo.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
		List<Menu> list = jdbcTemplate.query("SELECT m.id as id, m.name as menu_name, price, category_id, c.name AS category_name, description, review_amount, review_star_amount FROM menu AS m INNER JOIN categories AS c ON category_id = c.id WHERE m.id = :id ORDER BY m.id", param, new BeanPropertyRowMapper<Menu>(Menu.class));
        return list.isEmpty() ? null : list.get(0);
	}
	
	@Override //合ってるか不安すぎる！
	public int deleteById(String id) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", Integer.parseInt(id));
		return jdbcTemplate.update("DELETE FROM products WHERE product_id = :id", param);
	}
	
	@Override //どんな型でも addValueでいけるんかな？
	public int updateById(int id, String name, int price, int category, String description, int nowId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		param.addValue("name", name);
		param.addValue("price", price);
		param.addValue("c_id", category);
		param.addValue("description", description);
		param.addValue("up_date", new Timestamp(System.currentTimeMillis()));
		param.addValue("current_id", nowId);
		return jdbcTemplate.update("UPDATE products SET product_id = :id, name = :name, price = :price, category_id = :c_id, description = :description, updated_at = :up_date WHERE product_id = :current_id", param);
	}
	
	@Override
	public List<Menu> findByCategory(int categoryId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("category_id", categoryId);
		return jdbcTemplate.query("SELECT mc.id AS id, menu_name, price, category_id, category_name, description, COUNT(r.id) AS review_amount, SUM(star) AS review_star_amount FROM (SELECT m.id as id, m.name as menu_name, price, category_id, c.name AS category_name, description FROM menu AS m INNER JOIN categories AS c ON category_id = c.id WHERE category_id = :category_id) AS mc LEFT JOIN reviews AS r ON mc.id = menu_id GROUP BY mc.id, mc.menu_name, mc.price, mc.category_id, mc.category_name, mc.description", param, new BeanPropertyRowMapper<Menu>(Menu.class));
		//return jdbcTemplate.query("SELECT m.id as id, m.name as menu_name, price, category_id, c.name AS category_name, description, review_amount, review_star_amount FROM menu AS m INNER JOIN categories AS c ON category_id = c.id WHERE category_id = :category_id ORDER BY m.id", param, new BeanPropertyRowMapper<Menu>(Menu.class));
		// https://loglog.xyz/programming/java/jdbctemplate_query_select で table名とEntityの結びつきについて学べるよ！！
	}
	
	@Override
	public List<Menu> findByKeyword(String keyword) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("keyword", "%" + keyword + "%");
		return jdbcTemplate.query("SELECT product_id, category_id, c.name AS category_name, p.name AS name, price, description, p.created_at AS p_created_at, p.updated_at AS p_updated_at FROM products AS p INNER JOIN categories AS c ON p.category_id = c.id WHERE p.name LIKE :keyword OR c.name LIKE :keyword ORDER BY product_id", param, new BeanPropertyRowMapper<Menu>(Menu.class));
	}
	
	@Override // createdatについては反映されない、それは引数productでそれがnullになっているから。見直し必要かも
	public int insert(Menu menu) {
		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(menu);
        return jdbcTemplate.update("INSERT INTO products (product_id, category_id, name, price, description, created_at, updated_at) VALUES (:productId, :categoryId, :name, :price, :description, :createdDate, :createdDate)", paramSource);
	}

}