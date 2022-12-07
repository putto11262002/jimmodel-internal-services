package com.jimmodel.internalServices.service;

import com.jimmodel.internalServices.exception.ValidationException;
import com.jimmodel.internalServices.model.*;
import com.jimmodel.internalServices.exception.ResourceNotFoundException;
import com.jimmodel.internalServices.repository.ModelRepository;
import com.jimmodel.internalServices.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OptionServiceImpl implements OptionService{

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private JobService jobService;

    @Autowired
    private Validator validator;

    @Autowired
    private ModelRepository modelRepository;


    @Override
    public Event save(Event option) {
        option.toOption();
        Set<ConstraintViolation<BaseEntity>> violations = validator.validate(option, Event.OptionInfo.class);
        if(!violations.isEmpty()){
            throw new ValidationException("Option validation failed", violations.stream().map(violation -> violation.getMessage()).collect(Collectors.toList()));
        }
        Collection<Model> relatedModels = new HashSet<>();

        if(option.getRelatedModels() != null){
            option.getRelatedModels().forEach(model -> {
                if (model.getId() == null ) return;
                Optional<Model> existingModel = modelRepository.findById(model.getId());
                if(existingModel.isPresent()) relatedModels.add(existingModel.get());
            });
        }

        option.setRelatedModels(relatedModels);
        option.setType(Event.TYPE.OPTION);
        return eventRepository.save(option);
    }

    @Override
    public Event saveById(UUID id, Event updatedOption) {
        Event option = this.findById(id);
        option.setSlots(updatedOption.getSlots());
        option.setTitle(updatedOption.getTitle());
        option.setWorkingHour(updatedOption.getWorkingHour());
        option.setMediaReleased(updatedOption.getMediaReleased());
        option.setTerritoriesReleased(updatedOption.getTerritoriesReleased());
        return this.save(option);
    }

    @Override
    public Event findById(UUID id) {
        return eventRepository.findByTypeAndId(Event.TYPE.OPTION, id).orElseThrow(() -> new ResourceNotFoundException(String.format("Option with id %s does not exist", id)));
    }

    @Override
    public Page<Event> findAll(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = sortBy.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Event> optionPage =  eventRepository.findAllByType(Event.TYPE.OPTION, pageable);
        return optionPage;
    }

    @Override
    @Transactional
    public Event toJob(UUID id) {
        Event option = this.findById(id);
        eventRepository.deleteById(id);
        Event createdJob = jobService.save(Event.builder()
                        .title(option.getTitle())
                        .slots(option.getSlots())
                        .mediaReleased(option.getMediaReleased())
                        .territoriesReleased(option.getTerritoriesReleased())
                        .workingHour(option.getWorkingHour())
                        .type(Event.TYPE.JOB)
                .build());
        return createdJob;
    }

    @Override
    public void deleteById(UUID id) {
        if(!eventRepository.existsByTypeAndId(Event.TYPE.OPTION, id)){
            new ResourceNotFoundException(String.format("Option with id %s does not exist", id));
        }
        eventRepository.deleteById(id);
    }

    @Override
    public Page<Event> search(String searchTerm, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = sortBy.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return eventRepository.search(Event.TYPE.OPTION, searchTerm, pageable);
    }
}
