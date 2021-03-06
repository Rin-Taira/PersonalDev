package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
    public User authentication(String id, String pass) {
        return userDao.findByIdAndPass(id, pass);
    }

	@Override
	public User findById(int id) {
		return userDao.findById(id);
	}
}

