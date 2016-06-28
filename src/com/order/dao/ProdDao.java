package com.order.dao;

import java.sql.SQLException;
import java.util.List;

import com.order.domain.Product;

public interface ProdDao extends Dao {

	/**
	 * 添加商品
	 * 
	 * @param prod
	 */
	void addProd(Product prod);

	/**
	 * 查询所有商品
	 * 
	 * @return
	 */
	List<Product> findAllProd();

	/**
	 * 查询指定管理员的商品
	 * 
	 * @param admin_id
	 * @return
	 */
	List<Product> findProdByAdmin(Integer admin_id);

	/**
	 * 根据id查找商品
	 * 
	 * @param id
	 * @return
	 */
	Product findProdById(String id);

	/**
	 * 扣除商品库存数量
	 * 
	 * @param product_id
	 * @param buynum
	 * @throws SQLException
	 */
	void delPnum(String product_id, int buynum) throws SQLException;

	/**
	 * 加回产品库存数量
	 * 
	 * @param product_id
	 * @param buynum
	 */
	void addPnum(String product_id, int buynum);

	/**
	 * 查询数据库中一共有多少条记录
	 * 
	 * @return
	 */
	int getCountRow();

	/**
	 * 查询指定记录后多少条记录
	 * 
	 * @param from
	 *            从哪条记录后取
	 * @param count
	 *            取多少条
	 * @return
	 */
	List<Product> getProdByPage(int from, int count);

	/**
	 * 修改商品信息
	 * 
	 * @param prod
	 *            最新信息bean
	 */
	void updateProd(Product prod);

	/**
	 * 根据id删除商品
	 * 
	 * @param id
	 */
	void delProdById(String id);
}
