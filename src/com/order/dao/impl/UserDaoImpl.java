package com.order.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.order.dao.UserDao;
import com.order.domain.User;
import com.order.form.Courier;
import com.order.util.TransactionManager;

public class UserDaoImpl implements UserDao {

	public void addUser(User user) {
		String sql = "insert into users values(null,?,?,?,?)";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql, user.getUsername(), user.getPassword(),
					user.getTelNum(), user.getRole_id());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public User findUserByName(String username) {
		String sql = "select * from users where username = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class),
					username);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void delUser(Integer id) {
		String sql = "delete from users where id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public User finUserByNameAndPsw(String username, String password) {
		String sql = "select * from users where username = ? and password = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class),
					username, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public User findUserById(Integer user_id) {
		String sql = "select * from users where id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner
					.query(sql, new BeanHandler<User>(User.class), user_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public User finUserByNameAndPswAndType(String username, String password,
			Integer role) {
		String sql = "select * from users where username = ? and password = ? and role_id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class),
					username, password, role);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Courier> findAllCouriers() {
		String sql = "select u.id, u.username, u.telNum from users u where u.role_id = 1";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql,
					new BeanListHandler<Courier>(Courier.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateUser(User user) {
		String sql = "update users set username = ?, telNum = ? where id=?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql, user.getUsername(), user.getTelNum(),
					user.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
