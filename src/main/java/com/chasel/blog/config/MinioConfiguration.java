package com.chasel.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 获取minio配置文件信息
 * 
 * @author chasel
 *
 */
@Component
@ConfigurationProperties(prefix = "minio", ignoreUnknownFields = false)
public class MinioConfiguration {

	private String url;
	private String sort;
	private String accesskey;
	private String secretkey;
	private String bucket;

	public String getUrl() {
		return url;
	}

	public String getSort() {
		return sort;
	}

	public String getAccesskey() {
		return accesskey;
	}

	public String getSecretkey() {
		return secretkey;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}

	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

}
