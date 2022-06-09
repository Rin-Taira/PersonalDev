package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Menu;

public interface MenuDao {

	public Menu findById(String id);

	public List<Menu> findByCategory(int categoryId);

}