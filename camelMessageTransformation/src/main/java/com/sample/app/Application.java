package com.sample.app;

import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import com.sample.app.routes.FileCopyRoute;

public class Application {
	
	public static void main(String args[]) throws Exception {
		CamelContext context = new DefaultCamelContext();

		context.addRoutes(new FileCopyRoute());

		context.start();

		TimeUnit.MINUTES.sleep(1);

		context.stop();
	}

}
