package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MenuDao;
import com.example.demo.entity.Menu;
import com.example.demo.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;
	
	@Override
	public List<Menu> findByCategory(int categoryId) {
		return menuDao.findByCategory(categoryId);
	}

	@Override
	public List<Menu> findByKeyword(String keyword) {
		return menuDao.findByKeyword(keyword);
	}

	@Override
	public Menu findById(String id) {
		return menuDao.findById(id);
	}

	@Override
	public int deleteById(String id) {
		return menuDao.deleteById(id);
	}
	
	@Override
	public int updateById(int id, String name, int price, int category, String description, int nowId) {
		return menuDao.updateById(id, name, price, category, description, nowId);
	}

	@Override
	public int insert(Menu product) {
		return menuDao.insert(product);
	}

}