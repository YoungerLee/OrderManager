package com.order.dao;

import java.sql.SQLException;
import java.util.List;

import com.order.domain.User;
import com.order.form.Courier;

public interface UserDao extends Dao {

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 *            用户名
	 * @param conn
	 * @return 查找到的用户,如果找不到返回null
	 */
	User findUserByName(String username);

	/**
	 * 添加用户
	 * 
	 * @param user
	 *            封装了用户信息的bean
	 * @param conn
	 */
	void addUser(User user);

	/**
	 * 根据id删除用户
	 * 
	 * @param id
	 *            要删除的用户id
	 */
	void delUser(Integer id);

	/**
	 * 根据用户名密码查找用户
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return 找到的用户bean
	 */
	User finUserByNameAndPsw(String username, String password);

	/**
	 * 根据id查找用户
	 * 
	 * @param user_id
	 * @return
	 */
	User findUserById(Integer user_id);

	/**
	 * 根据用户名密码和登录类型查找用户
	 * 
	 * @param username
	 * @param password
	 * @param role
	 * @return
	 */
	User finUserByNameAndPswAndType(String username, String password,
			Integer role);

	/**
	 * 获取所有快递员信息
	 * 
	 * @return 返回的结果集
	 */
	List<Courier> findAllCouriers();

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 */
	void updateUser(User user);

	/**
	 * 查找用户姓名
	 * 
	 * @param id
	 * @return
	 */
	User findUsernameById(Integer id);

	/**
	 * 扣除余额
	 * 
	 * @param id
	 * @param money
	 */
	void decCash(Integer id, Double money) throws SQLException;

	/**
	 * 增加余额
	 * 
	 * @param id
	 * @param money
	 */
	void incCash(Integer id, Double money);
}
