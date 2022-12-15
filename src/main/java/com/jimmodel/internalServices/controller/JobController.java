package com.jimmodel.internalServices.controller;

import com.jimmodel.internalServices.dto.Request.JobRequest;
import com.jimmodel.internalServices.dto.Response.JobResponse;
import com.jimmodel.internalServices.dto.Response.JobsResponse;
import com.jimmodel.internalServices.domain.Event;
import com.jimmodel.internalServices.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping()
    public ResponseEntity<JobResponse> createJob(@RequestBody JobRequest jobRequest){
        Event createdJob = jobService.save(jobRequest.toEntity());
        JobResponse responseBody = new JobResponse(createdJob);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJobById(@PathVariable(name = "id")UUID id){
        Event job = jobService.findById(id);
        JobResponse responseBody = new JobResponse(job);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> getUpdateById(@PathVariable(name = "id") UUID id, @RequestBody JobRequest jobRequest){
        Event updatedJob = jobService.saveById(id, jobRequest.toEntity());
        JobResponse responseBody = new JobResponse(updatedJob);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<JobsResponse> getJobs(
            @RequestParam(required = false, defaultValue = "0", name = "pageNumber") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "${data.page-size}", name = "pageSize") Integer pageSize,
            @RequestParam(required = false, defaultValue = "${data.job.sort-by}", name = "sortBy") String sortBy,
            @RequestParam(required = false, defaultValue = "${data.sort-dir}", name = "sortDir") String sortDir
    ){

        Page<Event> jobPage = jobService.findAll(pageNumber, pageSize, sortBy, sortDir);
        JobsResponse responseBody = new JobsResponse(jobPage);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(name = "id") UUID id){
        jobService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/search/{searchTerm}")
    public ResponseEntity<JobsResponse> searchJobs(
            @PathVariable(value = "searchTerm") String searchTerm,
            @RequestParam(required = false, defaultValue = "0", name = "pageNumber") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "${data.page-size}", name = "pageSize") Integer pageSize,
            @RequestParam(required = false, defaultValue = "${data.job.sort-by}", name = "sortBy") String sortBy,
            @RequestParam(required = false, defaultValue = "${data.sort-dir}", name = "sortDir") String sortDir
            ){
        Page<Event> jobPage = jobService.search(searchTerm, pageNumber, pageSize, sortBy, sortDir);
        JobsResponse responseBody = new JobsResponse(jobPage);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
