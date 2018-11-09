package com.sample.app.route;

import org.apache.camel.builder.RouteBuilder;

public class FileCopyRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("textFilesOnly:C:\\Users\\Public\\demo?oneLevel=false")
				.to("textFilesOnly:C:\\Users\\Public\\demoTxtFiles");
	}

}