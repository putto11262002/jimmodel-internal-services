package com.jimmodel.internalServices.service;

import com.jimmodel.internalServices.model.Slot;

import java.time.Instant;
import java.util.Collection;
import java.util.Map;

public interface CalendarService {

    public Map<Instant, Collection<Slot>> getMonthCalendar(Integer month, Integer year);

    public Map<Instant, Collection<Slot>> getWeekCalendar(Integer week, Integer year);
}
