package com.jimmodel.internalServices.dto.Request;

import com.jimmodel.internalServices.domain.Event;
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
        return Event.reminderBuilder(
                this.id,
                this.title,
                this.slots != null ? this.slots.stream().map(slotRequest -> slotRequest.toEntity()).collect(Collectors.toList()) : null,
                this.relatedModels != null ? this.relatedModels.stream().map(modelRequest -> modelRequest.toEntity()).collect(Collectors.toList()) : null,
                this.note
        );

    }
}