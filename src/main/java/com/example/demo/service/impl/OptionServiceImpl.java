package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OptionDao;
import com.example.demo.service.OptionService;

@Service
public class OptionServiceImpl implements OptionService {
	
	@Autowired
	private OptionDao optionDao;
	
	@Override
	public int searchOptionPrice(int id) {
            return optionDao.searchOptionPrice(id);
    }

}

