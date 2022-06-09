package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Review;


public interface ReviewService {

	public List<Review> findById(String id);
	
	public int insertReview(Review review);

}