package com.jimmodel.internalServices.service;

import com.jimmodel.internalServices.model.Event;
import com.jimmodel.internalServices.model.Slot;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

public interface CalendarService {

    public Map<LocalDate, Collection<Slot>> getMonthlyCalendar(Integer month, Integer year);

}
