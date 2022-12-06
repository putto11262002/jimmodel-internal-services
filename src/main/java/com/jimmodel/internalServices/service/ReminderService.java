package com.jimmodel.internalServices.service;

import com.jimmodel.internalServices.model.Event;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ReminderService extends CrudService<Event, UUID> {
}
