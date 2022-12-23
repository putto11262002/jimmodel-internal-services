package com.jimmodel.internalServices.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Builder
@Getter
@Setter
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
    @NotNull(message = "Invalid gender.")
    private EGender gender;
    private String nationality;
    private String ethnicity;
    private String countryOfResidence;
    private String spokenLanguage;
    private String passportNumber;
    private String idCard;
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
    private Boolean inTown = false;
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
    private Boolean published;
    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Measurement measurement;

    @Builder
    public Model(UUID id, String firstName, String lastName, String otherNames, String email, String phoneNumber, String lineId, String whatsApp, String weChat, String instagram, String facebook, LocalDate dateOfBirth, EGender gender, String nationality, String ethnicity, String countryOfResidence, String spokenLanguage, String passportNumber, String idCard, String taxId, String occupation, String education, Address address, String talent, String medicalBackground, String tattooScar, Boolean underwearShooting, Boolean inTown, Collection<Image> compCardImage, Image profileImage, Collection<ModelExperience> experiences, Boolean published, Measurement measurement) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherNames = otherNames;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.lineId = lineId;
        this.whatsApp = whatsApp;
        this.weChat = weChat;
        this.instagram = instagram;
        this.facebook = facebook;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.nationality = nationality;
        this.ethnicity = ethnicity;
        this.countryOfResidence = countryOfResidence;
        this.spokenLanguage = spokenLanguage;
        this.passportNumber = passportNumber;
        this.idCard = idCard;
        this.taxId = taxId;
        this.occupation = occupation;
        this.education = education;
        this.address = address;
        this.talent = talent;
        this.medicalBackground = medicalBackground;
        this.tattooScar = tattooScar;
        this.underwearShooting = underwearShooting;
        this.inTown = inTown;
        this.compCardImage = compCardImage;
        this.profileImage = profileImage;
        this.experiences = experiences;
        this.published = published == null ? false : published;
        this.measurement = measurement;
    }

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
