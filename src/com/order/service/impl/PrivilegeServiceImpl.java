package com.order.service.impl;

import java.util.Set;

import com.order.dao.PrivilegeDao;
import com.order.factory.BasicFactory;
import com.order.service.PrivilegeService;

public class PrivilegeServiceImpl implements PrivilegeService {
	PrivilegeDao dao = BasicFactory.getFactory().getDao(PrivilegeDao.class);

	@Override
	public Set<String> getPrivilegeByRole(Integer role_id) {
		return dao.findPrivilegeByRole(role_id);
	}
}
