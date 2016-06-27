package com.order.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.order.dao.ProdDao;
import com.order.domain.Product;
import com.order.util.TransactionManager;

public class ProdDaoImpl implements ProdDao {

	public void addProd(Product prod) {
		String sql = "insert into products values (?,?,?,?,?,?,?)";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql, prod.getId(), prod.getName(), prod.getImgurl(),
					prod.getPrice(), prod.getPnum(), prod.getStartTime(),
					prod.getEndTime());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Product> findAllProd() {
		String sql = "select * from products";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql,
					new BeanListHandler<Product>(Product.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Product findProdById(String id) {
		String sql = "select * from products where id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<Product>(Product.class),
					id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void delPnum(String product_id, int buynum) throws SQLException {
		String sql = "update products set pnum = pnum-? where id = ? and pnum-?>=0";
		QueryRunner runner = new QueryRunner(TransactionManager.getSource());
		int count = runner.update(sql, buynum, product_id, buynum);
		if (count <= 0) {
			throw new SQLException("库存不足!");
		}
	}

	public void addPnum(String product_id, int buynum) {
		String sql = "update products set pnum = pnum+? where id = ? ";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql, buynum, product_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getCountRow() {
		String sql = "select count(*) from products";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return ((Long) runner.query(sql, new ScalarHandler())).intValue();// 单行数据处理
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Product> getProdByPage(int from, int count) {
		String sql = "select * from products limit ?,?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql,
					new BeanListHandler<Product>(Product.class), from, count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateProd(Product prod) {
		String sql = "update products set name=? ,imgurl=? ,price=?,pnum=?,startTime=?, endTime=? where id=?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql, prod.getName(), prod.getImgurl(),
					prod.getPrice(), prod.getPnum(), prod.getStartTime(),
					prod.getEndTime(), prod.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delProdById(String id) {
		String sql = "delete from products where id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
