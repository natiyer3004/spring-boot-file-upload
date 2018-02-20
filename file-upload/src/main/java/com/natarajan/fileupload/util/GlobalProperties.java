package com.natarajan.fileupload.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:global.properties")
@ConfigurationProperties
public class GlobalProperties {

	private String uploadFolder;

	public String getUploadFolder() {
		return uploadFolder;
	}

	public void setUploadFolder(String uploadFolder) {
		this.uploadFolder = uploadFolder;
	}
}
