package com.example.demo.dao.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.ManagerDao;
import com.example.demo.entity.Manager;

@Repository
public class ManagerDaoImpl implements ManagerDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public int updateManager() {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("date", LocalDate.now());
		return jdbcTemplate.update("UPDATE manager SET updated_date = :date WHERE updated_date = (SELECT MIN(updated_date) FROM manager)", param);
	}
	
	@Override
	public int getTodayManager() {
		List<Manager> list = jdbcTemplate.query("SELECT * FROM manager WHERE to_char(updated_date, 'YYYY-mm=dd') = to_char(now(), 'YYYY-mm=dd')", new BeanPropertyRowMapper<Manager>(Manager.class));
		if (list.isEmpty()) { //今日の日付がないということはupdated_dateが一番若い人が注文担当になる。
			list = jdbcTemplate.query("SELECT user_id, updated_date FROM manager WHERE updated_date = (SELECT MIN(updated_date) FROM manager)", new BeanPropertyRowMapper<Manager>(Manager.class));
		} else {
			list = jdbcTemplate.query("SELECT user_id, updated_date FROM manager WHERE updated_date = (SELECT MAX(updated_date) FROM manager)", new BeanPropertyRowMapper<Manager>(Manager.class));
		}
		return list.get(0).getUserId();
	}
	
	@Override
	public int isCompleteTodayOrder() {
		List<Manager> list = jdbcTemplate.query("SELECT * FROM manager WHERE to_char(updated_date, 'YYYY-mm=dd') = to_char(now(), 'YYYY-mm=dd')", new BeanPropertyRowMapper<Manager>(Manager.class));
		if(list.isEmpty()) {
			return 0;
		}
		return 1;
	}
    
}
