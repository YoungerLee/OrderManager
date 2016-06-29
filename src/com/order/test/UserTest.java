package com.order.test;

import java.util.Set;

import org.junit.Test;

import com.order.dao.PrivilegeDao;
import com.order.dao.UserDao;
import com.order.factory.BasicFactory;

public class UserTest {
	private UserDao dao = BasicFactory.getFactory().getDao(UserDao.class);
	private PrivilegeDao pdao = BasicFactory.getFactory().getDao(
			PrivilegeDao.class);

	// @Test
	// public void testUsername() {
	// System.out.println(dao.findUsernameById(4).getUsername());
	// }
	@Test
	public void testPri() {
		Set<String> set = pdao.findPrivilegeByRole(0);
		for (String str : set) {
			System.out.println(str);
		}
	}
}
