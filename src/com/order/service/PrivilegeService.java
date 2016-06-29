package com.order.service;

import java.util.Set;

public interface PrivilegeService extends Service {
	/**
	 * 根据角色查找对应的权限
	 * 
	 * @param role_id
	 * @return 路径的集合
	 */
	Set<String> getPrivilegeByRole(Integer role_id);
}
