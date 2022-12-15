package com.jimmodel.internalServices.dto.Response;

import com.jimmodel.internalServices.domain.Slot;
import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Data
public class MonthlyCalendarResponse {
    private Map<String, Collection<SlotResponse>> calendar = new TreeMap<>();
    private String month;


    public MonthlyCalendarResponse(Map<LocalDate, Collection<Slot>> calendar, String month){
        calendar.forEach((date, slots) -> {
            Collection<SlotResponse> slotsResponse = slots.stream().map(slot -> new SlotResponse(slot)).collect(Collectors.toList());
            this.calendar.put(date.toString(), slotsResponse);
        });
        this.month = month;
    }
}
