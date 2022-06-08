package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Review;


public interface ReviewService {

//	public List<Menu> findByCategory(int categoryId);

	public List<Review> findById(String id);
	
	public int insertReview(Review review);

//	public Menu findById(String id);
//	
//	public int deleteById(String id);
//	
//	public int updateById(int id, String name, int price, int category, String description, int nowId);
//
//	public int insert(Menu menu);

}