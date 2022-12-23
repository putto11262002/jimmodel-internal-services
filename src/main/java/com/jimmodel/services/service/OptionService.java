package com.jimmodel.services.service;

import com.jimmodel.services.domain.Event;

import java.util.UUID;

public interface OptionService extends CrudService<Event, UUID> {

    public Event toJob(UUID id);

}
