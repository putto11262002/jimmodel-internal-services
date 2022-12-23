package com.jimmodel.services.dto.Response;

import com.jimmodel.services.domain.Measurement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MeasurementResponse {
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

    public MeasurementResponse(Measurement measurement){
        this.height = measurement.getHeight();
        this.weight = measurement.getWeight();
        this.chest = measurement.getChest();
        this.collar = measurement.getCollar();
        this.aroundArmpit = measurement.getAroundArmpit();
        this.aroundThickToAnkle = measurement.getAroundThickToAnkle();
        this.aroundArmToWrist1 = measurement.getAroundArmToWrist1();
        this.aroundArmToWrist2 = measurement.getAroundArmToWrist2();
        this.aroundArmToWrist3 = measurement.getAroundArmToWrist3();
        this.armLength1 = measurement.getArmLength1();
        this.armLength2 = measurement.getArmLength2();
        this.trouserLength = measurement.getTrouserLength();
        this.chestHeight = measurement.getChestHeight();
        this.chestWidth = measurement.getChestWidth();
        this.waist = measurement.getWaist();
        this.hips = measurement.getHips();
        this.shoulder = measurement.getShoulder();
        this.frontShoulder = measurement.getFrontShoulder();
        this.backShoulder = measurement.getBackShoulder();
        this.frontLength = measurement.getFrontLength();
        this.backLength = measurement.getBackLength();
        this.crotch = measurement.getCrotch();
        this.suitDressSize = measurement.getSuitDressSize();
        this.braSize = measurement.getBraSize();
        this.shoesSize = measurement.getBraSize();
        this.eyeColour = measurement.getEyeColour();
        this.hairColour = measurement.getHairColour();
    }
}
