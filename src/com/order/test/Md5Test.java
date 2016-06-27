package com.order.test;

import org.junit.Test;

import com.order.util.MD5Utils;

public class Md5Test {
	@Test
	public void testMD5() {
		String str = "admin";
		String md5Str = MD5Utils.md5(str);
		System.out.println(md5Str);
	}
}
