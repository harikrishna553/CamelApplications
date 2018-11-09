package com.sample.app.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.builder.RouteBuilder;

public class FileCopyRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		String sourceFolder = "file:C:\\Users\\Public\\sourceFolder?noop=true";
		String destFolder = "file:C:\\Users\\Public\\destFolder";

		from(sourceFolder).transform(new Expression() {

			@Override
			public <T> T evaluate(Exchange exchange, Class<T> type) {
				String body = exchange.getIn().getBody(String.class);
				return (T) body.toUpperCase();
			}

		}).to(destFolder);

	}

}