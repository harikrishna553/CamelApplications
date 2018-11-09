package com.sample.app.formatters;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.camel.Exchange;
import org.apache.camel.spi.DataFormat;

public class TextReverseFormatter implements DataFormat {

	@Override
	public void marshal(Exchange exchange, Object graph, OutputStream stream) throws Exception {
		byte[] bytes = exchange.getContext().getTypeConverter().mandatoryConvertTo(byte[].class, graph);
		String body = reverseData(bytes);
		stream.write(body.getBytes());
	}

	@Override
	public Object unmarshal(Exchange exchange, InputStream stream) throws Exception {
		byte[] bytes = exchange.getContext().getTypeConverter().mandatoryConvertTo(byte[].class, stream);
		String body = reverseData(bytes);
		return body;
	}

	private static String reverseData(byte[] data) {
		StringBuilder builder = new StringBuilder();

		for (int i = data.length - 1; i >= 0; i--) {
			builder.append((char) data[i]);
		}

		return builder.toString();
	}

}
