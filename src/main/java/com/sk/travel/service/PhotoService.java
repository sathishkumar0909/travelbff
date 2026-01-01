package com.sk.travel.service;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class PhotoService {

    private final GridFSBucket gridFSBucket;

    @Autowired
    public PhotoService(GridFSBucket gridFSBucket) {
        this.gridFSBucket = gridFSBucket;
    }

    // Upload photo
    public String uploadPhoto(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            ObjectId fileId = gridFSBucket.uploadFromStream(file.getOriginalFilename(), inputStream);
            return fileId.toHexString();
        }
    }

    // Fetch photo
    public byte[] getPhoto(String fileId) throws IOException {
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(new ObjectId(fileId));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IOUtils.copy(downloadStream, outputStream);
        return outputStream.toByteArray();
    }
}
