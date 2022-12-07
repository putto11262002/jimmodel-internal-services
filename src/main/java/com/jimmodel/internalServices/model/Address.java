package com.jimmodel.internalServices.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "address")
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator",strategy = "com.jimmodel.internalServices.config.UUIDGenerator")
    @EqualsAndHashCode.Include private UUID id;
    @NotBlank(message = "Address cannot be blank.")
    private String address;
    @NotBlank(message = "City cannot be blank.")
    private String city;
    @NotBlank(message = "Province cannot be blank")
    private String province;
    @NotBlank(message = "Postal code cannot be blank.")
    private String postalCode;
    @NotBlank(message = "Country cannot be blank.")
    private String country;
}
