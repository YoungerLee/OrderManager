package com.order.service;

import java.util.List;

import com.order.domain.PageProd;
import com.order.domain.Product;

public interface ProdService extends Service {

	/**
	 * 添加商品
	 * 
	 * @param prod
	 *            商品信息bean
	 */
	void addProd(Product prod);

	/**
	 * 查询所有商品
	 * 
	 * @return
	 */
	List<Product> findAllProd();

	/**
	 * 查询指定管理员的所有商品
	 * 
	 * @param admin_id
	 * @return
	 */
	List<Product> findProdByAdmin(Integer admin_id);

	/**
	 * 根据id查找商品信息
	 * 
	 * @param id
	 * @return
	 */
	Product findProdById(String id);

	/**
	 * 分页查询商品的方法
	 * 
	 * @param thispage
	 *            当前页码
	 * @param rowperpage
	 *            每页记录数
	 * @return 当前页所有信息
	 */
	PageProd pageProd(int thispage, int rowperpage);

	/**
	 * 修改商品信息的方法
	 * 
	 * @param prod
	 *            封装了最新商品信息的bean
	 */
	void updateProd(Product prod);

	/**
	 * 根据id删除商品
	 * 
	 * @param id
	 */
	void delProdById(String id);
}
