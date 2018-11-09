package com.sample.app.producer;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;

import com.sample.app.endpoint.TextFileTransferEndpoint;

public class TextFileTransferProducer extends DefaultProducer {

	private String rootDirectoryPath;

	public TextFileTransferProducer(Endpoint endpoint) {
		super(endpoint);
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		String obj = (String) exchange.getIn().getBody();
		exchange.getOut().copyFrom(exchange.getIn());

		TextFileTransferEndpoint textFileTransferEndPoint = (TextFileTransferEndpoint) super.getEndpoint();
		this.rootDirectoryPath = textFileTransferEndPoint.getFilePath();

		String relativePath = (String) exchange.getIn().getHeader("relativePath");
		String fullPath = rootDirectoryPath + File.separator + relativePath;
		//Create parent directories if not exists
		new File(fullPath).getParentFile().mkdirs();
		Files.write(Paths.get(fullPath), obj.getBytes());
	}

}
