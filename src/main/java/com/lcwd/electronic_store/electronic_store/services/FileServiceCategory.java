package com.lcwd.electronic_store.electronic_store.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileServiceCategory {
    String fileUpload(MultipartFile file,String path) throws IOException;
    InputStream getFileResource(String path, String name) throws FileNotFoundException;
}
