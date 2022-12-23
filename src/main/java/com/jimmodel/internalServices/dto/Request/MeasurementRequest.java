package com.jimmodel.internalServices.dto.Request;

import com.jimmodel.internalServices.domain.Measurement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MeasurementRequest {

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

    public Measurement toEntity(){
        return Measurement.builder()
                .height(this.height)
                .weight(this.weight)
                .chest(this.chest)
                .collar(this.collar)
                .aroundArmpit(this.aroundArmpit)
                .aroundThickToAnkle(this.aroundThickToAnkle)
                .aroundArmToWrist1(this.aroundArmToWrist1)
                .aroundArmToWrist2(this.aroundArmToWrist2)
                .aroundArmToWrist3(this.aroundArmToWrist3)
                .armLength1(this.armLength1)
                .armLength2(this.armLength2)
                .trouserLength(this.trouserLength)
                .chestHeight(this.chestHeight)
                .chestWidth(this.chestWidth)
                .waist(this.waist)
                .hips(this.hips)
                .shoulder(this.shoulder)
                .frontShoulder(this.frontShoulder)
                .backShoulder(this.backShoulder)
                .frontLength(this.frontLength)
                .backLength(this.backLength)
                .crotch(this.crotch)
                .suitDressSize(this.suitDressSize)
                .braSize(this.braSize)
                .shoesSize(this.shoesSize)
                .eyeColour(this.eyeColour)
                .hairColour(this.hairColour)
                .build();
    }
}
