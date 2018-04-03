package com.chasel.blog.service.impl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.chasel.blog.exception.BlogException;
import com.chasel.blog.service.IExportBaseService;
import com.chasel.blog.util.IoUtils;

@Service
public class ExportBaseService implements IExportBaseService {

	@Override
	public void downloadFile(byte[] bytes, String fileName) throws BlogException {
		try {
			IoUtils.downloadFile(bytes, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void downloadFile(HSSFWorkbook workBook, String fileName) throws BlogException {
		try {
			IoUtils.downloadFile(workBook, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
