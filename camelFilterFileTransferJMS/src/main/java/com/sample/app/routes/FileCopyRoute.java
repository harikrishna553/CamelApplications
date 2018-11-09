package com.sample.app.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;

public class FileCopyRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		String allFilesQ = "demoJMS:queue:AllFilesQ";
		String textFilesQ = "demoJMS:queue:TextFilesQ";
		String lowSizeQ = "demoJMS:queue:LowSizeQ";

		Predicate onlyTextFiles = new Predicate() {
			@Override
			public boolean matches(Exchange exchange) {
				String fileName = (String) exchange.getIn().getHeader("CamelFileName");
				return fileName.endsWith(".txt");
			}
		};

		Predicate filesBelow1000Bytes = new Predicate() {
			@Override
			public boolean matches(Exchange exchange) {
				long fileLength = (Long) exchange.getIn().getHeader("CamelFileLength");
				return fileLength <= 1000;
			}
		};

		from("file:C:\\Users\\Public\\demo?noop=true").to(allFilesQ).filter(onlyTextFiles).to(textFilesQ)
				.filter(filesBelow1000Bytes).to(lowSizeQ);
	}
}
