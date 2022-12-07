package com.jimmodel.internalServices.controller;

import com.jimmodel.internalServices.dto.Response.MonthlyCalendarResponse;
import com.jimmodel.internalServices.model.Event;
import com.jimmodel.internalServices.model.Slot;
import com.jimmodel.internalServices.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Map;

@Controller
@RequestMapping(value = "/${api-version}/calendar")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @GetMapping(value = "/monthly/{year}/{month}")
    public ResponseEntity<MonthlyCalendarResponse> getMonthlyCalendar(@PathVariable(value = "year") Integer year, @PathVariable(value = "month") Integer month){
        Map<String, Collection<Slot>> calendar =  calendarService.getMonthCalendar(month - 1, year);
        MonthlyCalendarResponse responseBody = new MonthlyCalendarResponse(calendar, String.format("%s-%s", year, month));
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

}
