package com.jimmodel.internalServices.service;

import com.jimmodel.internalServices.domain.Event;

import java.util.UUID;

public interface OptionService extends CrudService<Event, UUID> {

    public Event toJob(UUID id);

}
