package com.order.listener;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.order.domain.Product;

public class MyHSessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent se) {
		se.getSession().setAttribute("cartmap",
				new LinkedHashMap<Product, Integer>());
	}

	public void sessionDestroyed(HttpSessionEvent se) {

	}
}
