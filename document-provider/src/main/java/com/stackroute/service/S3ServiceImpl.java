package com.stackroute.service;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


@Service
public class S3ServiceImpl implements S3Service {


    private String awsS3AudioBucket;
    private AmazonS3 amazonS3;
    private static final Logger logger = LoggerFactory.getLogger(S3ServiceImpl.class);

    Marker marker;

    @Autowired
    public S3ServiceImpl(Region awsRegion, AWSCredentialsProvider awsCredentialsProvider, String awsS3AudioBucket)
    {
        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(awsRegion.getName()).build();
        this.awsS3AudioBucket = awsS3AudioBucket;
    }

    /* @Async to make them asynchronous which means these methods will be executed in some other
      background thread except the main thread
     */
    @Async
    public void uploadFileToS3Bucket(MultipartFile multipartFile, boolean enablePublicReadAccess)
    {
        String fileName = multipartFile.getOriginalFilename();

        OutputStream stream = null;
        try {
            //creating the file in the server (temporarily)
            File file = new File(fileName);
            stream  = new FileOutputStream(file);
            stream.write(multipartFile.getBytes());
            stream.close();

            PutObjectRequest putObjectRequest = new PutObjectRequest(this.awsS3AudioBucket, fileName, file);

            /* Below code segment is responsible for adding PublicRead permissions
               to the file being uploaded
             */
            if (enablePublicReadAccess) {
                putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
            }
            this.amazonS3.putObject(putObjectRequest);

            //removing the file created in the server
            Files.delete(Paths.get(fileName));
            if(!file.delete()){
                logger.debug(marker,"file not deleted");
            }


        } catch (IOException | AmazonServiceException ex) {
//            logger.log("error [" + ex.getMessage() + "] occurred while uploading [" + fileName + "] ");
            logger.debug(marker,"message {}");
        }
    }

    /* A serviceImpl method to delete a file from S3 bucket */

    @Async
    public void deleteFileFromS3Bucket(String fileName)
    {
        try {
            amazonS3.deleteObject(new DeleteObjectRequest(awsS3AudioBucket, fileName));
        } catch (AmazonServiceException ex) {
//            logger.error("error [" + ex.getMessage() + "] occurred while removing [" + fileName + "] ");
            logger.debug(marker,"message {}");
        }
    }
}
