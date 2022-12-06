package com.jimmodel.internalServices.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    public void save(String DirName, String fileName, MultipartFile file);

    public Resource load(String filePath);

    public void delete(String filePath) ;

    public void init();
}
