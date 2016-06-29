package com.order.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.order.dao.PrivilegeDao;
import com.order.domain.Privilege;
import com.order.util.TransactionManager;

public class PrivilegeDaoImpl implements PrivilegeDao {

	@Override
	public Set<String> findPrivilegeByRole(Integer role_id) {
		List<Privilege> list = null;
		Set<String> set = new HashSet<String>();
		String sql = "select p.role_id, f.path from roles r, function f, privilege p "
				+ "where p.role_id = ? and f.id = p.func_id;";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			list = runner.query(sql, new BeanListHandler<Privilege>(
					Privilege.class), role_id);
			for (Privilege p : list) {
				set.add(p.getPath());
			}
			return set;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
