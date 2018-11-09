package com.sample.app.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;

public class FileCopyRoute extends RouteBuilder {

	Predicate onlyTextFiles = new Predicate() {

		@Override
		public boolean matches(Exchange exchange) {
			String fileName = (String) exchange.getIn().getHeader("CamelFileName");
			return fileName.endsWith(".txt");
		}

	};

	@Override
	public void configure() throws Exception {
		from("file:C:\\Users\\Public\\demo?noop=true").filter(onlyTextFiles).to("file:C:\\Users\\Public\\demoCopy");
	}

}
