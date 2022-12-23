package com.jimmodel.services.dto.Request;


import com.jimmodel.services.domain.EGender;
import com.jimmodel.services.domain.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Setter
@Getter
public class ModelRequest {
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
    private String idCard;
    private String taxId;
    private String occupation;
    private String education;
    private AddressRequest address;
    private String talent;
    private String medicalBackground;
    private String tattooScar;
    private Boolean underwearShooting;
    private Boolean inTown;
    private Collection<ModelExperienceRequest> experiences;

    private MeasurementRequest measurement;

    public Model toEntity(){
        EGender eGender;
        switch (this.gender.toLowerCase()){
            case "male":
                eGender = EGender.MALE;
                break;
            case "female":
                eGender = EGender.FEMALE;
                break;
            case "other":
                eGender = EGender.OTHER;
                break;
            default:
                eGender = null;
        }
        return Model.builder()
                .id(this.id)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .otherNames(this.otherNames)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .lineId(this.lineId)
                .weChat(this.weChat)
                .whatsApp(this.whatsApp)
                .instagram(this.instagram)
                .facebook(this.facebook)
                .gender(eGender)
                .nationality(this.nationality)
                .ethnicity(this.ethnicity)
                .countryOfResidence(this.countryOfResidence)
                .spokenLanguage(this.spokenLanguage)
                .idCard(this.idCard)
                .taxId(this.taxId)
                .occupation(this.occupation)
                .education(this.education)
                .passportNumber(this.passportNumber)
                .talent(this.talent)
                .medicalBackground(this.medicalBackground)
                .tattooScar(this.tattooScar)
                .underwearShooting(this.underwearShooting)
                .inTown(this.inTown)
                .experiences(this.experiences != null ? this.experiences.stream().map(experiencesRequest -> experiencesRequest.toEntity()).collect(Collectors.toList()) : null)
                .address(this.address != null ? this.address.toEntity() : null)
                .dateOfBirth(this.dateOfBirth)
                .measurement(this.measurement != null ? this.measurement.toEntity() : null)
                .build();
    }
}
