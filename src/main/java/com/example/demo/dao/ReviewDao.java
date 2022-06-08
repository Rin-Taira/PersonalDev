package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Review;

public interface ReviewDao {

    public List<Review> findById(String id);
    
    public int insertReview(Review review);
    
}
