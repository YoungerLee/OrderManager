package com.order.service.impl;

import java.util.List;

import com.order.dao.UserDao;
import com.order.domain.User;
import com.order.factory.BasicFactory;
import com.order.form.Courier;
import com.order.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao dao = BasicFactory.getFactory().getDao(UserDao.class);

	public void regist(User user) {
		try {
			// 1.校验用户名是否已经存在
			if (dao.findUserByName(user.getUsername()) != null) {
				throw new RuntimeException("用户名已经存在!!");
			}
			// 2.调用dao中的方法添加用户到数据库
			user.setRole_id(2);
			dao.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addCourier(User courier) {
		try {
			// 1.校验用户名是否已经存在
			if (dao.findUserByName(courier.getUsername()) != null) {
				throw new RuntimeException("用户名已经存在!!");
			}
			// 2.调用dao中的方法添加用户到数据库
			courier.setRole_id(1);
			dao.addUser(courier);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public User getUserByNameAndPsw(String username, String password) {
		return dao.finUserByNameAndPsw(username, password);
	}

	@Override
	public User getUserByNameAndPswAndRole(String username, String password,
			Integer role) {
		return dao.finUserByNameAndPswAndType(username, password, role);
	}

	@Override
	public List<Courier> findAllCouriers() {
		return dao.findAllCouriers();
	}

	@Override
	public User findUserById(Integer id) {
		return dao.findUserById(id);
	}

	@Override
	public void updateCourier(Courier courier) {
		User user = new User();
		user.setId(courier.getId());
		user.setUsername(courier.getUsername());
		user.setTelNum(courier.getTelNum());
		dao.updateUser(user);
	}

	@Override
	public void delUserByID(Integer id) {
		dao.delUser(id);
	}
}