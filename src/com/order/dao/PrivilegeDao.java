package com.order.dao;

import java.util.Set;

public interface PrivilegeDao extends Dao {
	/**
	 * 查找对应角色的功能权限
	 * 
	 * @param role_id
	 * @return 路径集合
	 */
	Set<String> findPrivilegeByRole(Integer role_id);
}
