package com.sample.app.routes;

import org.apache.camel.builder.RouteBuilder;

import com.sample.app.translators.JsonToCsvTranslator;

public class FileCopyRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file:C:\\Users\\Public\\jsonOrders?noop=true").bean(new JsonToCsvTranslator())
				.to("file:C:\\Users\\Public\\csvOrders");
	}

}