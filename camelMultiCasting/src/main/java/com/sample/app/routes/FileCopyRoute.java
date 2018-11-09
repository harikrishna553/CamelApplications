package com.sample.app.routes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class FileCopyRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		String queue1 = "demoJMS:queue:A";
		String queue2 = "demoJMS:queue:B";
		String queue3 = "demoJMS:queue:C";
		String queue4 = "demoJMS:queue:D";

		ExecutorService executor = Executors.newFixedThreadPool(20);

		from("file:C:\\Users\\Public\\demo?noop=true").to(queue1).multicast().stopOnException().parallelProcessing()
				.executorService(executor).to(queue2, queue3, queue4);

		from(queue2).process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				String fileName = (String) exchange.getIn().getHeader("CamelFileName");
				System.out.println("Queue2 received " + fileName);

			}
		});

		from(queue3).process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				String fileName = (String) exchange.getIn().getHeader("CamelFileName");
				System.out.println("Queue3 received " + fileName);

			}
		});

		from(queue4).process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				String fileName = (String) exchange.getIn().getHeader("CamelFileName");
				System.out.println("Queue4 received " + fileName);

			}
		});
	}
}
