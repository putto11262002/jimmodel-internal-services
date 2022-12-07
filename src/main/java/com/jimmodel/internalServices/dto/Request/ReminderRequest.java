package com.jimmodel.internalServices.dto.Request;

import com.jimmodel.internalServices.model.Event;
import lombok.*;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReminderRequest {

    private UUID id;
    private String title;
    private Collection<SlotRequest> slots;
    private Collection<ModelRequest> relatedModels;
    private String note;
    private Event.TYPE type;

    public Event toEntity(){
        return Event.builder()
                .title(this.title)
                .slots(this.slots != null ? this.slots.stream().map(slotRequest -> slotRequest.toEntity()).collect(Collectors.toList()) : null)
                .relatedModels(this.relatedModels != null ? this.relatedModels.stream().map(modelRequest -> modelRequest.toEntity()).collect(Collectors.toList()) : null)
                .note(this.note)
                .type(this.type)
                .build();
    }
}