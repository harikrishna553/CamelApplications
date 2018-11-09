package com.sample.app.consumer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultConsumer;

import com.sample.app.endpoint.TextFileTransferEndpoint;

public class TextFileTransferConsumer extends DefaultConsumer {
	private Endpoint endpoint;
	private String rootDirectoryPath;
	private File fileToCopy;
	private boolean oneLevel;

	public TextFileTransferConsumer(Endpoint endpoint, Processor processor) {
		super(endpoint, processor);
		this.endpoint = endpoint;
		TextFileTransferEndpoint textFileTransferEndPoint = (TextFileTransferEndpoint) endpoint;

		this.rootDirectoryPath = textFileTransferEndPoint.getFilePath();
		this.fileToCopy = new File(this.rootDirectoryPath);
		
		oneLevel = textFileTransferEndPoint.isOneLevel();
		

	}

	public static String getRelativePath(String parentPath, String childPath) {
		Path parentPATH = Paths.get(parentPath);
		Path childPATH = Paths.get(childPath);
		return parentPATH.relativize(childPATH).toString();
	}

	@Override
	protected void doStart() throws Exception {
		super.doStart();
		copyAllFiles(fileToCopy);
	}

	@Override
	protected void doStop() throws Exception {
		super.doStop();
	}

	public void copyAllFiles(final File folder) throws Exception {
		for (final File file : folder.listFiles()) {
			if (file.isDirectory() && !oneLevel) {
				copyAllFiles(file);
			} else {

				if (file.getName().endsWith(".txt")) {
					System.out.println("Copying the file " + file.getName());
					System.out.println(file.getName());
					Exchange exchange = createExchnage(file);
					getProcessor().process(exchange);
				}

			}
		}
	}

	private Exchange createExchnage(File file) throws IOException {
		String relativePath = getRelativePath(rootDirectoryPath, file.getAbsolutePath());

		Exchange exchange = endpoint.createExchange();
		String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
		exchange.getIn().setBody(content);
		exchange.getIn().setHeader("relativePath", relativePath);
		return exchange;
	}

}
