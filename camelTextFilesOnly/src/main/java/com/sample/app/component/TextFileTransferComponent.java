package com.sample.app.component;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

import com.sample.app.endpoint.TextFileTransferEndpoint;

public class TextFileTransferComponent extends DefaultComponent{

	@Override
	protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
		return new TextFileTransferEndpoint(uri, remaining, this);
	}

}
