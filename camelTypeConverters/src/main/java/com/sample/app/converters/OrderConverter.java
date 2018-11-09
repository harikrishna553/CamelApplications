package com.sample.app.converters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.camel.Exchange;
import org.apache.camel.TypeConversionException;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.support.TypeConverterSupport;

import com.sample.app.model.Order;
import com.sample.app.storage.OrdersInfo;

public class OrderConverter extends TypeConverterSupport {

	@Override
	public <T> T convertTo(Class<T> type, Exchange exchange, Object value) throws TypeConversionException {

		/* Get the order from GenericFile */
		if (value.getClass().equals(GenericFile.class)) {
			System.out.println("Converting GenericFile to Order");
			GenericFile genericFile = (GenericFile) value;
			File file = (File) genericFile.getBody();
			String content;
			try {
				content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
				int orderId = Integer.parseInt(content);
				return (T) OrdersInfo.getOrder(orderId);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/* Get the string representaion from order */
		if (value.getClass().equals(Order.class)) {
			System.out.println("Converting Order to String");
			return (T) value.toString();
		}

		/* Get the order from string */
		if (value.getClass().equals(String.class)) {
			System.out.println("Converting String to Order");
			int orderId = Integer.parseInt(value.toString());
			return (T) OrdersInfo.getOrder(orderId);
		}

		return null;
	}

}