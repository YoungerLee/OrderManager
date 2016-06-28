package com.order.service.impl;

import java.util.List;
import java.util.UUID;

import com.order.dao.ProdDao;
import com.order.domain.PageProd;
import com.order.domain.Product;
import com.order.factory.BasicFactory;
import com.order.service.ProdService;

public class ProdServiceImpl implements ProdService {
	ProdDao dao = BasicFactory.getFactory().getDao(ProdDao.class);

	public void addProd(Product prod) {
		prod.setId(UUID.randomUUID().toString());
		dao.addProd(prod);
	}

	public List<Product> findAllProd() {
		return dao.findAllProd();
	}

	@Override
	public List<Product> findProdByAdmin(Integer admin_id) {
		return dao.findProdByAdmin(admin_id);
	}

	public Product findProdById(String id) {
		return dao.findProdById(id);
	}

	@Override
	public PageProd pageProd(int thispage, int rowperpage) {
		PageProd pageProd = new PageProd();
		// --当前页
		pageProd.setThispage(thispage);
		// --每页记录数
		pageProd.setRowperpage(rowperpage);
		// --共有多少行
		int countrow = dao.getCountRow();
		pageProd.setCountrow(countrow);
		// --共有多少页
		int countpage = countrow / rowperpage
				+ (countrow % rowperpage == 0 ? 0 : 1);
		pageProd.setCountpage(countpage);
		// --首页
		pageProd.setFirstpage(1);
		// --尾页
		pageProd.setLastpage(countpage);
		// --上一页
		pageProd.setPrepage(thispage == 1 ? 1 : thispage - 1);
		// --下一页
		pageProd.setNextpage(thispage == countpage ? countpage : thispage + 1);
		// --当前页数据
		List<Product> list = dao.getProdByPage((thispage - 1) * rowperpage,
				rowperpage);
		pageProd.setList(list);

		return pageProd;
	}

	@Override
	public void updateProd(Product prod) {
		dao.updateProd(prod);
	}

	@Override
	public void delProdById(String id) {
		dao.delProdById(id);
	}
}
