package com.sample.app.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class FileCopyRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		String sourceFolder = "file:C:\\Users\\Public\\orders\\csvFiles";
		String jmsOrderQ = "demoJMS:queue:orderQueue";

		from(sourceFolder).unmarshal().csv().to(jmsOrderQ);
		

		from(jmsOrderQ).process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				String data = exchange.getIn().getBody(String.class);
				System.out.println("Received order : " + data);
			}

		});

	}

}
