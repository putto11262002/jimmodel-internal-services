package com.jimmodel.internalServices.util;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

public class StorageUtil {
    public static Boolean validateImageMediaType(MultipartFile file){
         return (file.getContentType().equals("image/png")
                || file.getContentType().equals("image/jpg")
                || file.getContentType().equals("image/jpeg"));
    }
}
