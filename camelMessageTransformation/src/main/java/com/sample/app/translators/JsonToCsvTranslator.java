package com.sample.app.translators;

import com.google.gson.Gson;
import com.sample.model.Order;

public class JsonToCsvTranslator {
	private static Gson gson = new Gson();

	public static String map(final String messageBody) {

		Order order = gson.fromJson(messageBody, Order.class);

		String csvData = order.getOrderId() + "," + order.getProductId() + "," + order.getNoOfProducts();

		System.out.println("Data from is transformed to " + csvData);

		return csvData;
	}

}
