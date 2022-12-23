package com.jimmodel.services.service;

import com.jimmodel.services.domain.Event;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ReminderService extends CrudService<Event, UUID> {
}
