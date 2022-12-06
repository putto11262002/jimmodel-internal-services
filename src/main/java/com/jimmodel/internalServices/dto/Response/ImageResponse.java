package com.jimmodel.internalServices.dto.Response;

import com.jimmodel.internalServices.model.Image;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ImageResponse {
    private String fileName;
    private String type;

    private UUID id;

    public ImageResponse(Image image){

        this.id = image.getId();
        this.fileName = image.getFileName();
        this.type = image.getType().toString();

    }
}
