package com.jimmodel.internalServices.dto.Request;

import com.jimmodel.internalServices.domain.ModelExperience;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ModelExperienceRequest {

    private UUID id;
    private Integer year;
    private String product;
    private String country;
    private String media;
    private String details;

    public ModelExperience toEntity(){
        return ModelExperience.builder()
                .year(this.year)
                .product(this.product)
                .country(this.country)
                .media(this.media)
                .details(this.details)
                .build();
    }
}
