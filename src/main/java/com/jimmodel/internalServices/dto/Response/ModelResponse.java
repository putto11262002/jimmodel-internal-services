package com.jimmodel.internalServices.dto.Response;


import com.jimmodel.internalServices.domain.EGender;
import com.jimmodel.internalServices.domain.Model;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ModelResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String otherNames;
    private String email;
    private String phoneNumber;
    private String lineId;
    private String whatsApp;
    private String weChat;
    private String instagram;
    private String facebook;
    private LocalDate dateOfBirth;
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
    private AddressResponse address;
    private String talent;
    private String medicalBackground;
    private String tattooScar;
    private Boolean underwearShooting;
    private Boolean inTown;
    private Collection<ImageResponse> compCardImages;
    private ImageResponse profileImage;
    private Collection<ModelExperienceResponse> experiences;
    private Boolean published;
    private MeasurementResponse measurement;
    public ModelResponse(Model model){
        this.id = model.getId();
        this.firstName = model.getFirstName();
        this.lastName = model.getLastName();
        this.otherNames = model.getOtherNames();
        this.email = model.getEmail();
        this.phoneNumber = model.getPhoneNumber();
        this.lineId = model.getLineId();
        this.whatsApp = model.getWhatsApp();
        this.weChat = model.getWeChat();
        this.instagram = model.getInstagram();
        this.facebook = model.getFacebook();
        this.address = model.getAddress() != null ?  new AddressResponse(model.getAddress()): null;
        this.gender = model.getGender().name();
        this.nationality = model.getNationality();
        this.ethnicity = model.getEthnicity();
        this.countryOfResidence = model.getCountryOfResidence();
        this.spokenLanguage = model.getSpokenLanguage();
        this.passportNumber = model.getPassportNumber();
        this.IdCard = model.getIdCard();
        this.taxId = model.getTaxId();
        this.occupation = model.getOccupation();
        this.education = model.getEducation();
        this.dateOfBirth = model.getDateOfBirth();
        this.talent = model.getTalent();
        this.medicalBackground = model.getMedicalBackground();
        this.tattooScar = model.getTattooScar();
        this.underwearShooting = model.getUnderwearShooting();
        this.inTown = model.getInTown();
        this.published = model.getPublished();
        this.experiences = model.getExperiences() != null ? model.getExperiences().stream().map(experience -> new ModelExperienceResponse(experience)).collect(Collectors.toList()) : new ArrayList<>();
        this.profileImage = model.getProfileImage() != null ? new ImageResponse(model.getProfileImage()): null;
        this.compCardImages = model.getCompCardImage() != null ? model.getCompCardImage().stream().map(image -> new ImageResponse(image)).collect(Collectors.toList()) : new ArrayList<>();
        this.measurement = model.getMeasurement() != null ? new MeasurementResponse(model.getMeasurement()) : null;
    }
}
