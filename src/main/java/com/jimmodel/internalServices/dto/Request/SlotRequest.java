package com.jimmodel.internalServices.dto.Request;

import com.jimmodel.internalServices.model.Slot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SlotRequest {

    private UUID id;
    private ZonedDateTime startTimestamp;
    private ZonedDateTime endTimestamp;
    private String type;


    public Slot toEntity(){
        return Slot.builder()
                .startTimestamp(this.startTimestamp.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime())
                .endTimestamp(this.endTimestamp.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime())
                .type(this.type)
                .id(this.id)
                .build();
    }
}
