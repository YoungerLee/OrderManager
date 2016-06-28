package com.order.test;

import org.junit.Test;

import com.order.dao.UserDao;
import com.order.factory.BasicFactory;

public class UserTest {
	private UserDao dao = BasicFactory.getFactory().getDao(UserDao.class);

	@Test
	public void testUsername() {
		System.out.println(dao.findUsernameById(4).getUsername());
	}
}
