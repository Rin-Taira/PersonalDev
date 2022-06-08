package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ReviewDao;
import com.example.demo.entity.Review;
import com.example.demo.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewDao reviewDao;

	@Override
    public List<Review> findById(String id) {
        return reviewDao.findById(id);
    }

	@Override
	public int insertReview(Review review) {
		return reviewDao.insertReview(review);
	}
}

