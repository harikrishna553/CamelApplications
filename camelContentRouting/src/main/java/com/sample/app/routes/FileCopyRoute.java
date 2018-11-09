package com.sample.app.routes;

import org.apache.camel.builder.RouteBuilder;

public class FileCopyRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file:C:\\Users\\Public\\demo?noop=true").choice().when(header("CamelFileName").endsWith("xlsx"))
				.to("file:C:\\Users\\Public\\demoCopyXlsFiles");
	}

}