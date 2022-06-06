package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Menu;


public interface MenuService {

	public List<Menu> findByCategory(int categoryId);

	public List<Menu> findByKeyword(String keyword);

	public Menu findById(String id);
	
	public int deleteById(String id);
	
	public int updateById(int id, String name, int price, int category, String description, int nowId);

	public int insert(Menu menu);

}