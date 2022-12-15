package com.jimmodel.internalServices.dto.Response;

import com.jimmodel.internalServices.domain.Event;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OptionResponse extends EventResponse{

    private UUID id;
    private String title;
    private Collection<SlotResponse> slots;
    private Collection<ModelResponse> relatedModels;
    private String mediaReleased;
    private String territoriesReleased;
    private String workingHour;
    private String note;
    private Event.TYPE type;

    public OptionResponse(Event option){
        this.id = option.getId();
        this.title = option.getTitle();
        this.slots = option.getSlots() != null ? option.getSlots().stream().map(slot -> new SlotResponse(slot)).collect(Collectors.toList()) : new ArrayList<>();
        this.relatedModels = option.getRelatedModels() != null ? option.getRelatedModels().stream().map(relatedModel -> new ModelResponse(relatedModel)).collect(Collectors.toList()) : new ArrayList<>();
        this.mediaReleased = option.getMediaReleased();
        this.territoriesReleased = option.getTerritoriesReleased();
        this.workingHour = option.getWorkingHour();
        this.note = option.getNote();
        this.type = option.getType();

    }
}
