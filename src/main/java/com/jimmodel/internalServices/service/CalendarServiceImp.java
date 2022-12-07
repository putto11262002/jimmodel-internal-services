package com.jimmodel.internalServices.service;

import com.jimmodel.internalServices.model.Event;
import com.jimmodel.internalServices.model.Slot;
import com.jimmodel.internalServices.repository.EventRepository;
import com.jimmodel.internalServices.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;

@Service(value = "calendarService")
public class CalendarServiceImp implements CalendarService{

    @Autowired
    private SlotRepository slotRepository;


    private static  Map<String, Map<String, Collection<Slot>>> monthlyCalendars = new HashMap<>();

    public void buildMonthlyCalendar(Integer month, Integer year){
        Map<String, Collection<Slot>> cal = new TreeMap<>();
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.set(year, month, 1, 0, 0, 0);
        c.setTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC")));
//        c.set(java.util.Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        while (true){
            c.add(Calendar.DATE, -1);
            if(c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) break;
        }

        while(true){
            System.out.println(LocalDateTime.ofInstant(c.toInstant(), ZoneOffset.UTC));
            Collection<Slot> calendarEvent = slotRepository.findAllSlotByDate(c.toInstant());
            cal.put(String.format("%s-%s-%s",  c.get(Calendar.YEAR), c.get(Calendar.MONTH),  c.get(Calendar.DATE)), calendarEvent);
            if(c.get(java.util.Calendar.MONTH) != java.util.Calendar.DECEMBER & c.get(java.util.Calendar.DAY_OF_WEEK) == java.util.Calendar.SUNDAY) break;
            c.add(java.util.Calendar.DATE, 1);
        }
        CalendarServiceImp.monthlyCalendars.put(String.format("%s-%s", year, month), cal);
    }

    @Override
    public Map<String, Collection<Slot>> getMonthCalendar(Integer month, Integer year) {
        this.buildMonthlyCalendar(Calendar.DECEMBER, 2022);
        return CalendarServiceImp.monthlyCalendars.get(String.format("%s-%s", 2022, Calendar.DECEMBER));
    }

    @Override
    public Map<String, Collection<Slot>> getWeekCalendar(Integer week, Integer year) {
        return null;
    }
}
