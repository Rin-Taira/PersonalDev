package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.ReviewDao;
import com.example.demo.entity.Review;

@Repository
public class ReviewDaoImpl implements ReviewDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Review> findById(String id) {
    	MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", Integer.parseInt(id));
		return jdbcTemplate.query("SELECT menu_id, menu_name, user_id, u.name AS user_name, star, review, review_date FROM (SELECT menu_id, m.name AS menu_name, user_id, star, review, review_date FROM reviews as r JOIN menu AS m ON menu_id = m.id WHERE menu_id = :id) AS r_and_m JOIN users as u ON user_id = u.id", param, new BeanPropertyRowMapper<Review>(Review.class));
    }
    
    @Override
    public int insertReview(Review review) {
    	BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(review);
    	return jdbcTemplate.update("INSERT INTO reviews (user_id, menu_id, star, review) VALUES (:userId, :menuId, :star, :review)", paramSource);
    }
    
}
