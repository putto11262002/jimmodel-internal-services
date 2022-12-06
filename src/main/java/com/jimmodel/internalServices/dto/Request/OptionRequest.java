package com.jimmodel.internalServices.dto.Request;

import com.jimmodel.internalServices.model.Event;
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
    private Collection<SlotResponse> slots;

    private Collection<ModelRequest> relatedModels;
    private String mediaReleased;
    private String territoriesReleased;
    private String workingHour;
    private String note;
    private String type;

    public Event toEntity(){
        return Event.builder()
                .title(this.title)
                .slots(this.slots != null ? this.slots.stream().map(slotResponse -> slotResponse.toEntity()).collect(Collectors.toList()) : null)
                .relatedModels(this.relatedModels != null ? this.relatedModels.stream().map(modelRequest -> modelRequest.toEntity()).collect(Collectors.toList()) : null)
                .mediaReleased(this.mediaReleased)
                .territoriesReleased(this.territoriesReleased)
                .workingHour(this.workingHour)
                .note(this.note)
                .type(this.type)
                .build();
    }
}
