package com.jimmodel.internalServices.service;

import com.jimmodel.internalServices.domain.Model;
import com.jimmodel.internalServices.domain.Image;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public interface ModelService extends CrudService<Model, UUID> {

    public Image saveProfileImageById(UUID id, MultipartFile file);

    public void deleteProfileImageById(UUID id);

    public Image saveCompCardImageById(UUID id, MultipartFile file);

    public void deleteCompCardImageByImageId(UUID id);

    public Resource getImage(UUID id, String fileName);


}
