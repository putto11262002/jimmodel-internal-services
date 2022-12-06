package com.jimmodel.internalServices.dto.Response;

import com.jimmodel.internalServices.model.Event;
import lombok.Data;
import org.springframework.scheduling.config.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class ReminderResponse {

    private UUID id;
    private String title;
    private Collection<SlotResponse> slots;
    private Collection<ModelResponse> relatedModels;
    private String note;
    private String type;

    public ReminderResponse(Event reminder){
        this.id = reminder.getId();
        this.title = reminder.getTitle();
        this.slots = reminder.getSlots() != null ? reminder.getSlots().stream().map(slot -> new SlotResponse(slot)).collect(Collectors.toList()) : new ArrayList<>();
        this.relatedModels = reminder.getRelatedModels() != null ? reminder.getRelatedModels().stream().map(relatedModel -> new ModelResponse(relatedModel)).collect(Collectors.toList()) : new ArrayList<>();
        this.note = reminder.getNote();
        this.type = reminder.getType();
    }
}
