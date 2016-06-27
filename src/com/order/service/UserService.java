package com.order.service;

import java.util.List;

import com.order.annotation.Tran;
import com.order.domain.User;
import com.order.form.Courier;

public interface UserService extends Service {

	/**
	 * 注册用户
	 * 
	 * @param user
	 *            封装了用户数据的userbean
	 */
	@Tran
	void regist(User user);

	/**
	 * 添加快递员
	 * 
	 * @param courier
	 */
	@Tran
	void addCourier(User courier);

	/**
	 * 根据用户名密码查找用户
	 * 
	 * @param username
	 * @param password
	 */
	User getUserByNameAndPsw(String username, String password);

	/**
	 * 根据用户名密码和登录类型查找用户
	 * 
	 * @param username
	 * @param password
	 * @param role
	 * @return
	 */
	User getUserByNameAndPswAndRole(String username, String password,
			Integer role);

	/**
	 * 查找所有快递员信息
	 * 
	 * @return 结果集
	 */
	List<Courier> findAllCouriers();

	/**
	 * 根据id查找用户
	 * 
	 * @param id
	 * @return
	 */
	User findUserById(Integer id);

	/**
	 * 修改快递员信息
	 * 
	 * @param courier
	 */
	void updateCourier(Courier courier);
}
