package com.jimmodel.internalServices.dto.Response;

import com.jimmodel.internalServices.domain.ModelExperience;
import lombok.Data;

import java.util.UUID;

@Data
public class ModelExperienceResponse  {
    private UUID id;
    private Integer year;
    private String product;
    private String country;
    private String media;
    private String details;
    public ModelExperienceResponse(ModelExperience experience) {
        this.id = experience.getId();
        this.year = experience.getYear();
        this.country = experience.getCountry();
        this.media = experience.getMedia();
        this.details = experience.getDetails();
        this.product = experience.getProduct();
    }
}
