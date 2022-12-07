package com.jimmodel.internalServices.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "model")
@EntityListeners(AuditingEntityListener.class)
@ToString
public class Model  extends BaseEntity{

    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator",strategy = "com.jimmodel.internalServices.config.UUIDGenerator")
    @NotNull(groups = {Event.JobInfo.class, Event.ReminderInfo.class, Event.OptionInfo.class}, message = "Model id cannot be blank.")
    @EqualsAndHashCode.Include private UUID id;
    @NotBlank(message = "First name cannot be blank.")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank.")
    private String lastName;
    private String otherNames;
    @NotBlank(message = "Email cannot be blank.")
    private String email;
    private String phoneNumber;
    private String lineId;
    private String whatsApp;
    private String weChat;
    private String instagram;
    private String facebook;
    private LocalDate dateOfBirth;
    @NotBlank(message = "Gender cannot be blank.")
    private String gender;
    private String nationality;
    private String ethnicity;
    private String countryOfResidence;
    private String spokenLanguage;
    private String passportNumber;
    private String IdCard;
    private String taxId;
    private String occupation;
    private String education;
    @Valid
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    private String talent;
    private String medicalBackground;
    private String tattooScar;
    private Boolean underwearShooting;
    private Boolean inTown;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Collection<Image> compCardImage;
    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "profile_image_id", referencedColumnName = "id")
    private Image profileImage;
    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<ModelExperience> experiences;
//    @CreationTimestamp
//    private LocalDate createdAt;
//    @UpdateTimestamp
//    private LocalDateTime updatedAt;

    public void setExperiences(Collection<ModelExperience> experiences){
        this.experiences.clear();
        if(experiences != null){
            this.experiences.addAll(experiences);
        }
    }

    public void setCompCardImage(Collection<Image> compCardImage){
        this.compCardImage.clear();
        if(compCardImage != null){
            this.compCardImage.addAll(compCardImage);
        }
    }


    public void addExperience(ModelExperience experience){
        this.experiences.add(experience);
    }

    public void removeExperienceById(Long id){
        this.getExperiences().removeIf(experience -> experience.getId().equals(id));
    }

    public void addCompCardImage(Image image){
        this.getCompCardImage().add(image);
    }

    public void removeCompCardImagesById(Long id){
        this.getCompCardImage().removeIf(image -> image.getId().equals(id));
    }
}
