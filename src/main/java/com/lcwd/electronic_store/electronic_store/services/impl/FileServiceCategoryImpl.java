package com.lcwd.electronic_store.electronic_store.services.impl;

import com.lcwd.electronic_store.electronic_store.exception.BadApiRequest;
import com.lcwd.electronic_store.electronic_store.services.FileServiceCategory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
@Service
public class FileServiceCategoryImpl implements FileServiceCategory {

    @Override
    public String fileUpload(MultipartFile file, String path) throws IOException {
        String fileName= UUID.randomUUID().toString();
        String originalFileName=file.getOriginalFilename();
        String extension=originalFileName.substring(originalFileName.lastIndexOf("."));
        String fullNameWitheExtension=fileName+extension;
        String fullNameWithPath=path+fullNameWitheExtension;
        if(extension.equalsIgnoreCase(".jpg")||extension.equalsIgnoreCase(".png")||extension.equalsIgnoreCase(".jpeg")){
            File folder=new File(path);
            if(!folder.exists()){
                folder.mkdirs();
            }
            Files.copy(file.getInputStream(), Paths.get(fullNameWithPath));
        }else {
            throw  new BadApiRequest("file with this extension "+extension+" not acceptable...");
        }
        return fullNameWitheExtension;
    }

    @Override
    public InputStream getFileResource(String path, String name) throws FileNotFoundException {
        String fullPath=path+name;
        InputStream file=new FileInputStream(fullPath);
        return file;
    }

}
