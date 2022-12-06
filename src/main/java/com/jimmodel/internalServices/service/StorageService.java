package com.jimmodel.internalServices.service;

import com.jimmodel.internalServices.config.StorageConfig;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;

public interface StorageService {

    public void save(String DirName, String fileName, MultipartFile file);

    public Resource load(String filePath);

    public void delete(String filePath) ;

    public void init();
}
