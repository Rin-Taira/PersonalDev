package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Menu;

public interface MenuDao {

	public Menu findById(String id);

	public int deleteById(String id);
	
	public int updateById(int id, String name, int price, int category, String description, int nowId);

	public List<Menu> findByCategory(int categoryId);

	public List<Menu> findByKeyword(String keyword);

	public int insert(Menu menu);

}