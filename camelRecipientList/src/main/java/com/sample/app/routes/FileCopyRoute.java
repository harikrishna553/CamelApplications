package com.sample.app.routes;

import java.util.Arrays;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class FileCopyRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		String allOrdersQ = "demoJMS:queue:AllOrdersQ";
		String highPrioOrdersQ = "demoJMS:queue:HighPrioOrdersQ";
		String normalPrioOrdersQ = "demoJMS:queue:NormalPrioordersQ";

		from("file:C:\\Users\\Public\\demo?noop=true").process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.getIn().setHeader("customer", "");

				String fileName = (String) exchange.getIn().getHeader("CamelFileName");
				if (fileName.endsWith(".txt")) {
					exchange.getIn().setHeader("customer", "ABC Corp");
				}

			}

		}).to(allOrdersQ);

		from(allOrdersQ).process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				List<String> recipients = Arrays.asList(normalPrioOrdersQ);

				String customer = exchange.getIn().getHeader("customer", String.class);
				String fileName = (String) exchange.getIn().getHeader("CamelFileName");

				if ("ABC Corp".equals(customer)) {
					System.out.println(fileName + " should sent to both the queues ");
					recipients = Arrays.asList(normalPrioOrdersQ, highPrioOrdersQ);
				}
				exchange.getIn().setHeader("recipients", recipients);
			}

		}).recipientList(header("recipients"));
		
		from(highPrioOrdersQ).process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				String fileName = (String) exchange.getIn().getHeader("CamelFileName");
				System.out.println("High prio order queue received " + fileName);
			}
			
		});
		
		from(normalPrioOrdersQ).process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				String fileName = (String) exchange.getIn().getHeader("CamelFileName");
				System.out.println("Normal prio order queue received " + fileName);
			}
			
		});

	}
}


