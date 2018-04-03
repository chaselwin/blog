package com.chasel.blog.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import com.chasel.blog.config.MinioConfiguration;
import com.chasel.blog.exception.BlogException;
import com.chasel.blog.service.IExportBaseService;
import com.chasel.blog.service.IMinioService;
import com.chasel.blog.util.IoUtils;

import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidArgumentException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidExpiresRangeException;
import io.minio.errors.InvalidPortException;
import io.minio.errors.NoResponseException;

/**
 * Minio文件服务器处理实现类
 * 
 * @author chasel
 *
 */
@Service
public class MinioService implements IMinioService {

	private Logger log = Logger.getLogger(MinioService.class);

	@Autowired
	private MinioConfiguration minioConfig;

	@Autowired
	private IExportBaseService exportBaseService;

	private MinioClient minioClient;

	@Override
	public boolean upload(MultipartFile file,String objectName) {
		try {

			minioClient = getMinioClient();// 连接文件服务器
			
			if (!minioClient.bucketExists(minioConfig.getBucket())) {
				minioClient.makeBucket(minioConfig.getBucket());
			}

			minioClient.putObject(minioConfig.getBucket(), objectName, file.getInputStream(),
					file.getSize(), file.getContentType());

			minioClient.traceOff();// 关闭资源
			
			return true;
		} catch (InvalidKeyException e) {
			log.error("InvalidKeyException------Minio------" + e.getMessage());

		} catch (InvalidBucketNameException e) {
			log.error("InvalidBucketNameException------Minio------" + e.getMessage());

		} catch (NoSuchAlgorithmException e) {
			log.error("NoSuchAlgorithmException------Minio------" + e.getMessage());

		} catch (InsufficientDataException e) {
			log.error("InsufficientDataException------Minio------" + e.getMessage());

		} catch (NoResponseException e) {
			log.error("NoResponseException------Minio------" + e.getMessage());

		} catch (ErrorResponseException e) {
			log.error("ErrorResponseException------Minio------" + e.getMessage());

		} catch (InternalException e) {
			log.error("InternalException------Minio------" + e.getMessage());

		} catch (InvalidArgumentException e) {
			log.error("InvalidArgumentException------Minio------" + e.getMessage());

		} catch (IOException e) {
			log.error("IOException------Minio------" + e.getMessage());

		} catch (XmlPullParserException e) {
			log.error("XmlPullParserException------Minio------" + e.getMessage());

		}
		return false;
	}

	@Override
	public String getUrl(String objectName) {
		try {
			minioClient = getMinioClient();
			return minioClient.presignedGetObject(minioConfig.getBucket(), objectName);

		} catch (InvalidKeyException e) {
			log.error("InvalidKeyException------Minio------" + e.getMessage());

		} catch (InvalidBucketNameException e) {
			log.error("InvalidBucketNameException------Minio------" + e.getMessage());

		} catch (NoSuchAlgorithmException e) {
			log.error("NoSuchAlgorithmException------Minio------" + e.getMessage());

		} catch (InsufficientDataException e) {
			log.error("InsufficientDataException------Minio------" + e.getMessage());

		} catch (NoResponseException e) {
			log.error("NoResponseException------Minio------" + e.getMessage());

		} catch (ErrorResponseException e) {
			log.error("ErrorResponseException------Minio------" + e.getMessage());

		} catch (InternalException e) {
			log.error("InternalException------Minio------" + e.getMessage());

		} catch (IOException e) {
			log.error("IOException------Minio------" + e.getMessage());

		} catch (XmlPullParserException e) {
			log.error("XmlPullParserException------Minio------" + e.getMessage());

		} catch (InvalidExpiresRangeException e) {
			log.error("InvalidExpiresRangeException------Minio------" + e.getMessage());
		}
		return null;
	}

	@Override
	public void download(String objectName) {
		try {

			InputStream is = getInputStream(objectName);
			byte[] bytes = IoUtils.toByteArrayL(is);
			exportBaseService.downloadFile(bytes,objectName.substring(objectName.lastIndexOf("/") + 1, objectName.length()));

		} catch (IOException e) {
			log.error("IOException------Minio------" + e.getMessage());

		} catch (BlogException e) {
			log.error("DuplicateRecordException------Minio------" + e.getMessage());

		}
	}

	@Override
	public InputStream getInputStream(String objectName) {

		try {

			minioClient = getMinioClient();
			return minioClient.getObject(minioConfig.getBucket(), objectName);

		} catch (InvalidKeyException e) {
			log.error("InvalidKeyException------Minio------" + e.getMessage());

		} catch (InvalidBucketNameException e) {
			log.error("InvalidBucketNameException------Minio------" + e.getMessage());

		} catch (NoSuchAlgorithmException e) {
			log.error("NoSuchAlgorithmException------Minio------" + e.getMessage());

		} catch (InsufficientDataException e) {
			log.error("InsufficientDataException------Minio------" + e.getMessage());

		} catch (NoResponseException e) {
			log.error("NoResponseException------Minio------" + e.getMessage());

		} catch (ErrorResponseException e) {
			log.error("ErrorResponseException------Minio------" + e.getMessage());

		} catch (InternalException e) {
			log.error("InternalException------Minio------" + e.getMessage());

		} catch (IOException e) {
			log.error("IOException------Minio------" + e.getMessage());

		} catch (XmlPullParserException e) {
			log.error("XmlPullParserException------Minio------" + e.getMessage());

		} catch (InvalidArgumentException e) {
			log.error("InvalidArgumentException------Minio------" + e.getMessage());
		}
		return null;
	}

	public MinioClient getMinioClient() {
		try {
			minioClient = new MinioClient(minioConfig.getUrl() + ":" + minioConfig.getSort(),
					minioConfig.getAccesskey(), minioConfig.getSecretkey());
			return minioClient;

		} catch (InvalidEndpointException e) {
			log.error("InvalidEndpointException------Minio------" + e.getMessage());

		} catch (InvalidPortException e) {
			log.error("InvalidPortException------Minio------" + e.getMessage());
		}
		return minioClient;
	}

	@Override
	public void remove(String objectName) {
		try {
			minioClient = getMinioClient();
			minioClient.removeObject(minioConfig.getBucket(), objectName);

		} catch (InvalidKeyException e) {
			log.error("InvalidKeyException------Minio------" + e.getMessage());

		} catch (InvalidBucketNameException e) {
			log.error("InvalidBucketNameException------Minio------" + e.getMessage());

		} catch (NoSuchAlgorithmException e) {
			log.error("NoSuchAlgorithmException------Minio------" + e.getMessage());

		} catch (InsufficientDataException e) {
			log.error("InsufficientDataException------Minio------" + e.getMessage());

		} catch (NoResponseException e) {
			log.error("NoResponseException------Minio------" + e.getMessage());

		} catch (ErrorResponseException e) {
			log.error("ErrorResponseException------Minio------" + e.getMessage());

		} catch (InternalException e) {
			log.error("InternalException------Minio------" + e.getMessage());

		} catch (IOException e) {
			log.error("IOException------Minio------" + e.getMessage());

		} catch (XmlPullParserException e) {
			log.error("XmlPullParserException------Minio------" + e.getMessage());

		}
	}

}
