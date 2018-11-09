package com.sample.app.storage;

import java.util.HashMap;
import java.util.Map;

import com.sample.app.model.Order;

public class OrdersInfo {

	private static Map<Integer, Order> map = new HashMap<> ();
	
	static {
		map.put(1,  new Order(1, "prod1:5, prod2:10, prod19:1"));
		map.put(2,  new Order(1, "prod1:6, prod2:11, prod19:2"));
		map.put(3,  new Order(1, "prod1:7, prod2:12, prod19:3"));
		map.put(4,  new Order(1, "prod1:8, prod2:13, prod19:4"));
	}
	
	public static Order getOrder(int orderId) {
		return map.get(orderId);
	}
}
