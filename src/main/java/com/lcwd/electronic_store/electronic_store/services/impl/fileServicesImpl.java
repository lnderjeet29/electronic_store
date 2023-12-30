package com.lcwd.electronic_store.electronic_store.services.impl;

import com.lcwd.electronic_store.electronic_store.exception.BadApiRequest;
import com.lcwd.electronic_store.electronic_store.services.fileServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class fileServicesImpl implements fileServices {
    Logger logger= LoggerFactory.getLogger(fileServicesImpl.class);
    @Override
    public String fileUpload(MultipartFile file, String path) throws IOException {
        String originalFileName=file.getOriginalFilename();
        logger.info("file name is {}",originalFileName);
        String fileName= UUID.randomUUID().toString();
        String extension=originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileNameWithExtension=fileName+extension;
        String fullFileName=path+fileNameWithExtension;
        logger.info("full file name is {}",fullFileName);

        if(extension.equalsIgnoreCase(".jpg")||extension.equalsIgnoreCase(".png")||extension.equalsIgnoreCase(".jpeg")){
            File folder=new File(path);
            if(!folder.exists()){
                logger.info("create folder .....");
                folder.mkdirs();
            }
            logger.info("copy the folder...");
            Files.copy(file.getInputStream(), Paths.get(fullFileName));
            return fileNameWithExtension;
        }else {
            throw new BadApiRequest("file with this "+extension+" not present...");
        }
    }

    @Override
    public InputStream getFileResource(String path, String name) throws FileNotFoundException {
        String fullPath=path+File.separator+name;
        InputStream inputStream=new FileInputStream(fullPath);
        return inputStream;
    }
}
