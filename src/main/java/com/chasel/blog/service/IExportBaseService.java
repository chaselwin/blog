package com.chasel.blog.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.chasel.blog.exception.BlogException;

/**
 * 导出（下载）文件公共底层服务
 * 
 * @author chasel
 *
 */
public interface IExportBaseService {

	/**
	 * 通用下载接口
	 * 
	 * @param bytes
	 * @param fileName
	 */
	public void downloadFile(byte[] bytes, String fileName) throws BlogException;

	/**
	 * Excel专用下载接口
	 * 
	 * @param workBook
	 */
	public void downloadFile(HSSFWorkbook workBook, String fileName) throws BlogException;
}
