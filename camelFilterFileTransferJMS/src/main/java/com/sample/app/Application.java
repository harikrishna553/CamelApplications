package com.sample.app;

import java.util.concurrent.TimeUnit;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import com.sample.app.routes.FileCopyRoute;

public class Application {
	public static void main(String args[]) throws Exception {
		CamelContext context = new DefaultCamelContext();

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

		context.addComponent("demoJMS", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

		context.addRoutes(new FileCopyRoute());

		context.start();

		TimeUnit.MINUTES.sleep(1);

		context.stop();
	}
}
