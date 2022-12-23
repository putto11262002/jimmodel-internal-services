package com.jimmodel.services.dto.Response;

import com.jimmodel.services.domain.Slot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SlotResponse {
    private UUID id;
    private ZonedDateTime startTimestamp;
    private ZonedDateTime endTimestamp;
    private SlotTaskResponse event;
    private String type;

    public SlotResponse(Slot slot){
        this.id = slot.getId();
        this.startTimestamp = ZonedDateTime.of(slot.getStartTimestamp(), ZoneOffset.UTC);
        this.endTimestamp = ZonedDateTime.of(slot.getEndTimestamp(), ZoneOffset.UTC);
        this.type = slot.getType();
//        System.out.println(event.getTask());
        this.event = slot.getEvent() != null ? new SlotTaskResponse(slot.getEvent()) : null;
    }
}
