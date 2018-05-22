package com.chasel.blog.service;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

/**
 * Minio底层封装代码
 * 
 * @author chasel
 *
 */
public interface IMinioService {
	/**
	 * 上传
	 */
	public boolean upload(MultipartFile file,String fileName);

	/**
	 * 获取文件URL
	 * 
	 * @return
	 */
	public String getUrl(String uri);

	/**
	 * 下载文件
	 */
	public void download(String uri);

	/**
	 * 获取文件流
	 * 
	 * @return
	 */
	public InputStream getInputStream(String uri);

	/**
	 * 删除文件
	 * 
	 * @param uri
	 */
	public void remove(String uri);
}
