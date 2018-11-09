package com.sample.app.routes;

import org.apache.camel.builder.RouteBuilder;

import com.sample.app.formatters.TextReverseFormatter;

public class FileCopyRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		String sourceFolder = "file:C:\\Users\\Public\\demo?noop=true";
		String destFolder = "file:C:\\\\Users\\\\Public\\\\demoCopy";

		from(sourceFolder).marshal(new TextReverseFormatter()).to(destFolder);

	}

}
