package com.sample.app.endpoint;

import org.apache.camel.Component;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;

import com.sample.app.consumer.TextFileTransferConsumer;
import com.sample.app.producer.TextFileTransferProducer;

@UriEndpoint(firstVersion = "1.0", scheme = "textFilesOnly", title = "TextFilesOnly", syntax = "textFilesOnly:filePath", consumerClass = TextFileTransferConsumer.class)
public class TextFileTransferEndpoint extends DefaultEndpoint {

	@UriPath(description = "path the folder")
	@Metadata(required = "true")
	private String filePath;

	@UriParam(label = "oneLevel")
	private boolean oneLevel;

	public TextFileTransferEndpoint(String endpointUri, String filePath, Component component) {
		super(endpointUri, component);
		this.filePath = filePath;
	}

	@Override
	public Producer createProducer() throws Exception {
		return new TextFileTransferProducer(this);
	}

	@Override
	public Consumer createConsumer(Processor processor) throws Exception {
		return new TextFileTransferConsumer(this, processor);
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public boolean isOneLevel() {
		return oneLevel;
	}

	public void setOneLevel(boolean oneLevel) {
		this.oneLevel = oneLevel;
	}

}
