package com.jimmodel.services.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "model_experience")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ModelExperience extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator",strategy = "com.jimmodel.services.config.UUIDGenerator")
    @EqualsAndHashCode.Include private UUID id;
    @NotNull(message = "Experience year cannot be blank.")
    private Integer year;
    @NotBlank(message = "Experience product cannot be blank.")
    private String product;
    @NotBlank(message = "Experience country cannot be blank.")
    private String country;
    @NotBlank(message = "Experience media cannot be blank.")
    private String media;
    private String details;

}
