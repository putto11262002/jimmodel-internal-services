package com.jimmodel.services.service;

import com.jimmodel.services.exception.ResourceNotFoundException;
import com.jimmodel.services.exception.ValidationException;
import com.jimmodel.services.domain.BaseEntity;
import com.jimmodel.services.domain.Event;
import com.jimmodel.services.domain.Model;
import com.jimmodel.services.repository.EventRepository;
import com.jimmodel.services.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.*;
import java.util.stream.Collectors;

@Service(value = "reminderService")
public class ReminderServiceImp implements ReminderService{

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private Validator validator;

    @Override
    public Event save(Event reminder) {
        Set<ConstraintViolation<BaseEntity>> violations = validator.validate(reminder, Event.ReminderInfo.class);
        if(!violations.isEmpty()){
            throw new ValidationException(String.join(",",  violations.stream().map(violation -> violation.getMessage()).collect(Collectors.toList())));
        }

        Collection<Model> relatedModels = new HashSet<>();

        if(reminder.getRelatedModels() != null){
            reminder.getRelatedModels().forEach(model -> {
                if (model.getId() == null ) return;
                Optional<Model> existingModel = modelRepository.findById(model.getId());
                if(existingModel.isPresent()) relatedModels.add(existingModel.get());
            });
        }

        reminder.setRelatedModels(relatedModels);

        return eventRepository.save(reminder);
    }

    @Override
    public Event saveById(UUID id, Event updatedReminder) {
        Event reminder = this.findById(id);
        reminder.setTitle(updatedReminder.getTitle());
        reminder.setNote(updatedReminder.getNote());
        reminder.setRelatedModels(updatedReminder.getRelatedModels());
        return this.save(reminder);
    }

    @Override
    public Event findById(UUID id) {
        return eventRepository.findByTypeAndId(Event.TYPE.REMINDER, id).orElseThrow(() -> new ResourceNotFoundException(String.format("Reminder with id %s does not exist.", id)));
    }

    @Override
    public Page<Event> findAll(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = sortBy.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return eventRepository.findAllByType(Event.TYPE.REMINDER, pageable);
    }

    @Override
    public void deleteById(UUID id) {
        if(!eventRepository.existsByTypeAndId(Event.TYPE.REMINDER, id)){
            throw new ResourceNotFoundException(String.format("Reminder with id %s does not exist.", id));
        }
        eventRepository.deleteById(id);
    }

    @Override
    public Page<Event> search(String searchTerm, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = sortBy.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return eventRepository.search(Event.TYPE.REMINDER, searchTerm, pageable);
    }
}
