package com.sample.app.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class FileCopyRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file:C:\\Users\\Public\\demo?noop=true").process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				String fileName = (String) exchange.getIn().getHeader("CamelFileName");
				long fileLength = (Long) exchange.getIn().getHeader("CamelFileLength");

				System.out.println(fileName + " of length " + fileLength + " is copied from file system to exchnage");
			}
		}).to("demoJMS:queue:camelFileTransfer");
	}

}
