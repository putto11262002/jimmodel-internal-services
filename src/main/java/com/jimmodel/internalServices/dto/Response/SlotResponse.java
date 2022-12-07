package com.jimmodel.internalServices.dto.Response;

import com.jimmodel.internalServices.model.Slot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SlotResponse {
    private UUID id;
    private Instant startTimestamp;
    private Instant endTimestamp;
    private SlotTaskResponse event;
    private String type;

    public SlotResponse(Slot slot){
        this.id = slot.getId();
        this.startTimestamp = slot.getStartTimestamp();
        this.endTimestamp = slot.getEndTimestamp();
        this.type = slot.getType();
//        System.out.println(event.getTask());
        this.event = slot.getEvent() != null ? new SlotTaskResponse(slot.getEvent()) : null;
    }
}
