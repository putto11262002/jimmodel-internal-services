package com.jimmodel.internalServices.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EventSavedEvent {
    private Event savedEvent;
    public EventSavedEvent(Event savedEvent){
        this.savedEvent = savedEvent;
    }
}
