package com.jimmodel.internalServices.service;

import com.jimmodel.internalServices.exception.ValidationException;
import com.jimmodel.internalServices.model.*;
import com.jimmodel.internalServices.exception.ResourceNotFoundException;
import com.jimmodel.internalServices.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

@Service
public class JobServiceImp implements JobService{
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    Validator validator;

    @Override
    public Event save(Event job) {
        Set<ConstraintViolation<BaseEntity>>  violations = validator.validate(job, Event.JobInfo.class);
        if (!violations.isEmpty()){
            throw new ValidationException("Job validation failed", violations);
        }

        Collection<Model> relatedModels = new HashSet<>();
        Client client = null;


        if(job.getClient() != null){
            Optional<Client> existingClient = clientRepository.findById(job.getClient().getId());
            if(existingClient.isPresent()) client = existingClient.get();
        }


        if (job.getRelatedModels() != null) {
            job.getRelatedModels().forEach(model -> {
                Optional<Model> existingModel = modelRepository.findById(model.getId());
                if (existingModel.isPresent()) relatedModels.add(existingModel.get());
            });
        }
//        if(job.getSlots() != null){
//            job.getSlots().forEach(slot -> slot.setEvent(job));
//        }

        job.setRelatedModels(relatedModels);
        job.setClient(client);
        job.setType(Event.TYPE.JOB);

        return eventRepository.save(job);
    }

    @Override
    @Transactional
    public Event saveById(UUID id, Event updatedJob) {
        Event job = this.findById(id);
//        Set<ConstraintViolation<BaseEntity>> violations = validator.validate(updatedJob);
//        if (!violations.isEmpty()){
//            throw new ValidationException("Job validation failed", violations);
//        }
        job.setSlots(updatedJob.getSlots());
//        if(job.getEvents() != null) job.getEvents().forEach(event -> event.setTask(job));
        job.setTitle(updatedJob.getTitle());
        job.setRelatedModels(updatedJob.getRelatedModels());
        job.setClient(updatedJob.getClient());
        job.setPersonInCharge(updatedJob.getPersonInCharge());
        job.setMediaReleased(updatedJob.getMediaReleased());
        job.setPeriodReleased(updatedJob.getPeriodReleased());
        job.setTerritoriesReleased(updatedJob.getTerritoriesReleased());
        job.setWorkingHour(updatedJob.getWorkingHour());
        job.setOvertimePerHour(updatedJob.getOvertimePerHour());
        job.setFeeAsAgreed(updatedJob.getFeeAsAgreed());
        job.setTermOfPayment(updatedJob.getTermOfPayment());
        job.setCancellationFee(updatedJob.getCancellationFee());
        job.setContractDetails(updatedJob.getContractDetails());
        return eventRepository.save(job);
    }

    @Override
    public Event findById(UUID id) {
        return eventRepository.findByTypeAndId(Event.TYPE.JOB, id).orElseThrow(() -> new ResourceNotFoundException(String.format("Job with id %s does not exist.", id)));
    }

    @Override
    public Page<Event> findAll(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = sortBy.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Event> jobPage =  eventRepository.findAllByType(Event.TYPE.JOB, pageable);
        return jobPage;
    }

    @Override
    public void deleteById(UUID id) {
        if(!eventRepository.existsByTypeAndId(Event.TYPE.JOB, id)){
            throw new ResourceNotFoundException(String.format("Job with id %s does not exist.", id));
        }
        eventRepository.deleteById(id);
    }

    @Override
    public Page<Event> search(String searchTerm, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = sortBy.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return eventRepository.search(Event.TYPE.JOB, searchTerm, pageable);
    }
}
