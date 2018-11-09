package com.sample.app.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class FileCopyRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		String sourceFolder = "file:C:\\Users\\Public\\demo?noop=true";
		String rawQueue = "demoJMS:queue:RawQueue";
		String xlsQueue = "demoJMS:queue:XlsQueue";
		String txtQueue = "demoJMS:queue:TxtQueue";
		String otherQueue = "demoJMS:queue:OtherQueue";
		String finalQueue = "demoJMS:queue:FinalQueue";

		from(sourceFolder).to(rawQueue);

		from(rawQueue).choice().when(header("CamelFileName").endsWith(".xlsx")).to(xlsQueue)
				.when(header("CamelFileName").endsWith(".txt")).to(txtQueue).stop().otherwise().to(otherQueue).end()
				.to(finalQueue);

		from(finalQueue).process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				String fileName = (String) exchange.getIn().getHeader("CamelFileName");
				System.out.println("FinalQueue  received " + fileName);

			}

		});

	}

}
