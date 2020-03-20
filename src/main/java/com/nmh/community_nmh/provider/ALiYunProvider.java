package com.nmh.community_nmh.provider;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.HeadObjectRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.nmh.community_nmh.exception.CustomizeErrorCode;
import com.nmh.community_nmh.exception.CustomizeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * @author niminui
 * @date 2020/3/19 11:22
 */
@Service
@Slf4j
public class ALiYunProvider {

    @Value("${aliyun.afile.endpoint}")
    private String endpoint;

    @Value("${aliyun.afile.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.afile.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.afile.bucketName}")
    private String bucketName;

    @Value("${aliyun.afile.fileImageName}")
    private String folder;

    @Value("${aliyun.afile.cacheControl}")
    private String cacheControl;

    @Value("${aliyun.afile.headKey}")
    private String headKey;

    @Value("${aliyun.afile.headValue}")
    private String headValue;

    @Value("${aliyun.afile.contentEncoding}")
    private String contentEncoding;

    @Value("${aliyun.afile.expirationTimes}")
    private Long expirationTimes;

    public URL upload(InputStream inputStream, String mineType, String fileName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String generatedFileName = "";
        String[] filePaths = fileName.split("\\.");
        if(filePaths.length > 1) {
            generatedFileName = UUID.randomUUID().toString() + "." + filePaths[filePaths.length - 1];
        } else  {
            return null;
        }
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(inputStream.available());
            metadata.setCacheControl(cacheControl);
            metadata.setHeader(headKey, headValue);
            metadata.setContentEncoding(contentEncoding);
            metadata.setContentType(mineType);
            PutObjectResult putResult = ossClient.putObject(bucketName, folder + generatedFileName, inputStream, metadata);
            if(!StringUtils.isEmpty(putResult.getETag())) {
                Date expiration = new Date(new Date().getTime() + expirationTimes);
                return ossClient.generatePresignedUrl(bucketName,
                        new StringBuilder(folder).append(generatedFileName).toString(), expiration);
            } else {
                log.error("ALiYun upload 上传图片失败！");
                throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ALiYun upload 上传图片失败！");
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        } finally {
            ossClient.shutdown();
        }
    }

}
