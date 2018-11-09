package com.sample.app.routes;

import org.apache.camel.builder.RouteBuilder;

public class FileCopyRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		String ordersQ = "demoJMS:queue:OrdersQ";
		String wireTappingQ = "demoJMS:queue:WireTappingQ";

		from("file:C:\\Users\\Public\\demo?noop=true").wireTap(wireTappingQ).to(ordersQ);
	}
}
