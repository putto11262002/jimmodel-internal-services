package com.jimmodel.internalServices.controller;

import com.jimmodel.internalServices.dto.Request.ReminderRequest;
import com.jimmodel.internalServices.dto.Response.ReminderResponse;
import com.jimmodel.internalServices.dto.Response.RemindersResponse;
import com.jimmodel.internalServices.model.Event;
import com.jimmodel.internalServices.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/v1/reminder")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    @PostMapping
    public ResponseEntity<ReminderResponse> createReminder(@RequestBody ReminderRequest reminderRequest){
        Event reminder = reminderService.save(reminderRequest.toEntity());
        ReminderResponse responseBody = new ReminderResponse(reminder);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ReminderResponse> updateReminderById(@PathVariable(value = "id") UUID id, @RequestBody ReminderRequest reminderRequest){
        Event updatedReminder = reminderService.saveById(id, reminderRequest.toEntity());
        ReminderResponse responseBody = new ReminderResponse(updatedReminder);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReminderResponse> getReminderById(@PathVariable(value = "id") UUID id){
        Event reminder = reminderService.findById(id);
        ReminderResponse responseBody = new ReminderResponse(reminder);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<RemindersResponse> getReminders(
            @RequestParam(required = false, defaultValue = "0", name = "pageNumber") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "${data.page-size}", name = "pageSize") Integer pageSize,
            @RequestParam(required = false, defaultValue = "${data.reminder.sort-by}", name = "sortBy") String sortBy,
            @RequestParam(required = false, defaultValue = "${data.sort-dir}", name = "sortDir") String sortDir
    ){
        Page<Event> reminderPage = reminderService.findAll(pageNumber, pageSize, sortBy, sortDir);
        RemindersResponse responseBody = new RemindersResponse(reminderPage);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteReminderById(@PathVariable(value = "id") UUID id){
        reminderService.deleteById(id);
        return  new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "search/{searchTerm}")
    public ResponseEntity<RemindersResponse> searchReminders(
            @PathVariable("searchTerm") String searchTerm,
            @RequestParam(required = false, defaultValue = "0", name = "pageNumber") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "${data.page-size}", name = "pageSize") Integer pageSize,
            @RequestParam(required = false, defaultValue = "${data.reminder.sort-by}", name = "sortBy") String sortBy,
            @RequestParam(required = false, defaultValue = "${data.sort-dir}", name = "sortDir") String sortDir
            ) {
        Page<Event> reminderPage = reminderService.search(searchTerm, pageNumber, pageSize, sortBy, sortDir);
        RemindersResponse responseBody = new RemindersResponse(reminderPage);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

}
