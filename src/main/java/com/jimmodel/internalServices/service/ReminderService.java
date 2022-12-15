package com.jimmodel.internalServices.service;

import com.jimmodel.internalServices.domain.Event;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ReminderService extends CrudService<Event, UUID> {
}
