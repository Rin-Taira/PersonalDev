package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.OptionDao;
import com.example.demo.entity.Option;

@Repository
public class OptionDaoImpl implements OptionDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public int searchOptionPrice(int id) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		List<Option> list = jdbcTemplate.query("SELECT * FROM options WHERE id = :id", param, new BeanPropertyRowMapper<Option>(Option.class));
        return (list.get(0)).getPrice();
	}
    
}
