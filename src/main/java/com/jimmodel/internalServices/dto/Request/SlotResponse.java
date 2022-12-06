package com.jimmodel.internalServices.dto.Request;

import com.jimmodel.internalServices.model.Slot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SlotResponse {

    private UUID id;
    private Instant startTimestamp;
    private Instant endTimestamp;


    public Slot toEntity(){
        return Slot.builder()
                .startTimestamp(startTimestamp)
                .endTimestamp(endTimestamp)
                .id(this.id)
                .build();
    }
}
