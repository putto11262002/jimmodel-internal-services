package com.jimmodel.internalServices.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator",strategy = "com.jimmodel.internalServices.config.UUIDGenerator")
    @EqualsAndHashCode.Include private UUID id;
    private String height;
    private String weight;
    private String chest;
    private String collar;
    private String aroundArmpit;
    private String aroundThickToAnkle;
    private String aroundArmToWrist1;
    private String aroundArmToWrist2;
    private String aroundArmToWrist3;
    private String armLength1;
    private String armLength2;
    private String trouserLength;
    private String chestHeight;
    private String chestWidth;
    private String waist;
    private String hips;
    private String shoulder;
    private String frontShoulder;
    private String backShoulder;
    private String frontLength;
    private String backLength;
    private String crotch;
    private String suitDressSize;
    private String braSize;
    private String shoesSize;
    private String hairColour;
    private String eyeColour;
}
