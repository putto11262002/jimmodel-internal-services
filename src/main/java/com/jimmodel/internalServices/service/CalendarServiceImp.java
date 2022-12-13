package com.jimmodel.internalServices.service;

import com.jimmodel.internalServices.model.EventSavedEvent;
import com.jimmodel.internalServices.model.Month;
import com.jimmodel.internalServices.model.Slot;
import com.jimmodel.internalServices.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;

@Service(value = "calendarService")
public class CalendarServiceImp implements CalendarService{

    @Autowired
    private SlotRepository slotRepository;

    private  Map<Month, Map<LocalDate, Collection<Slot>>> monthlyCalendars = new HashMap<>();

    @EventListener
    private void onEventSaved(EventSavedEvent event){
        clearCache();

    }

    public Map<LocalDate, Collection<Slot>> getMonthlyCalendar(Integer month, Integer year){
        Month targetMonth = new Month(year, month);
        if(this.monthlyCalendars.containsKey(targetMonth)){
            return this.monthlyCalendars.get(targetMonth);
        }
        buildMonthlyCalendar(targetMonth);
        return this.monthlyCalendars.get(targetMonth);
    }

    @Scheduled(fixedRate = 3600000)
    private void clearCache(){
        this.monthlyCalendars.clear();
    }
    public void buildMonthlyCalendar(Month month){
        Map<LocalDate, Collection<Slot>> cal = new TreeMap<>();
        LocalDateTime ldt = LocalDateTime.of(month.getYear(), month.getMonth(), 1, 0, 0, 0);
        while (true){
            ldt= ldt.minusDays(1);
            if(ldt.getDayOfWeek().equals(DayOfWeek.MONDAY)) break;
        }
        while(true){
            Collection<Slot> slots = slotRepository.findAllSlotByDate(ldt);
            cal.put(ldt.toLocalDate(), slots);
            ldt = ldt.plusDays(1);
            if(ldt.getMonthValue() != month.getMonth() & ldt.getDayOfWeek().equals(DayOfWeek.MONDAY)) break;

        }
        this.monthlyCalendars.put(month, cal);
    }


}
