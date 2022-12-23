package com.jimmodel.internalServices.service;

import com.jimmodel.internalServices.domain.Model;
import com.jimmodel.internalServices.domain.Image;
import org.h2.engine.Mode;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
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

    void publish(UUID id);

    void unpublish(UUID id);

    public Page<Model> findAllPublished(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);


}
