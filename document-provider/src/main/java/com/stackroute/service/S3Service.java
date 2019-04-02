package com.stackroute.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    //service method to upload a file to s3
    void uploadFileToS3Bucket(MultipartFile multipartFile, boolean enablePublicReadAccess);

    //service method to delete a file from s3
    void deleteFileFromS3Bucket(String fileName);
}
