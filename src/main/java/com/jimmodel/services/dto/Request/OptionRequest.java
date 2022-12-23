package com.jimmodel.services.dto.Request;

import com.jimmodel.services.domain.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OptionRequest {

    private UUID id;
    private String title;
    private Collection<SlotRequest> slots;

    private Collection<ModelRequest> relatedModels;
    private String mediaReleased;
    private String territoriesReleased;
    private String workingHour;
    private String note;
    private Event.TYPE type;

    public Event toEntity() {
        return Event.optionBuilder(
                this.id,
                this.title,
                this.slots != null ? this.slots.stream().map(slotRequest -> slotRequest.toEntity()).collect(Collectors.toList()) : null,
                this.relatedModels != null ? this.relatedModels.stream().map(modelRequest -> modelRequest.toEntity()).collect(Collectors.toList()) : null,
                this.mediaReleased,
                this.territoriesReleased,
                this.workingHour,
                this.note
        );
    }
}
