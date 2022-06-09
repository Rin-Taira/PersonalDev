package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ManagerDao;
import com.example.demo.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {
	
	@Autowired
	private ManagerDao managerDao;

	@Override
    public int updateManager() {
        return managerDao.updateManager();
    }
	
	@Override
	public int getTodayManager() {
		return managerDao.getTodayManager();
	}

	@Override
	public int isCompleteTodayOrder() {
		return managerDao.isCompleteTodayOrder();
	}
}

