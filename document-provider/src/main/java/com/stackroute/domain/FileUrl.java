package com.stackroute.domain;

/* A FileUrl class for setting and getting the URL of the file to be
   uploaded to the s3 storage
 */
public class FileUrl {
    private String fileUrl;

    public FileUrl(){

    }
    public FileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
