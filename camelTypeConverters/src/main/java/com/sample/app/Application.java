package com.sample.app;

import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.impl.DefaultCamelContext;

import com.sample.app.converters.OrderConverter;
import com.sample.app.model.Order;

public class Application {

	public static class FileCopyRoute extends RouteBuilder {

		@Override
		public void configure() throws Exception {
			System.out.println("Inside file Copy Route");
			from("file:C:\\Users\\Public\\demoOrders?noop=true").convertBodyTo(Order.class).convertBodyTo(String.class)
					.to("file:C:\\Users\\Public\\demoCopy");
		}

	}

	public static void main(String args[]) throws Exception {
		CamelContext context = new DefaultCamelContext();
		

		context.getTypeConverterRegistry().addTypeConverter(Order.class, GenericFile.class, new OrderConverter());
		context.getTypeConverterRegistry().addTypeConverter(String.class, Order.class, new OrderConverter());

		context.addRoutes(new FileCopyRoute());

		context.start();

		TimeUnit.MINUTES.sleep(1);

		context.stop();
	}
}
