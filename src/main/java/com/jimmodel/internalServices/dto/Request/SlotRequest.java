package com.jimmodel.internalServices.dto.Request;

import com.jimmodel.internalServices.model.Slot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SlotRequest {

    private UUID id;
    private Instant startTimestamp;
    private Instant endTimestamp;
    private String type;


    public Slot toEntity(){
        return Slot.builder()
                .startTimestamp(this.startTimestamp)
                .endTimestamp(this.endTimestamp)
                .type(this.type)
                .id(this.id)
                .build();
    }
}
