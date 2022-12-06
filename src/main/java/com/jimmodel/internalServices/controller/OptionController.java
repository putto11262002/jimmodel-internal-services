package com.jimmodel.internalServices.controller;

import com.jimmodel.internalServices.dto.Request.OptionRequest;
import com.jimmodel.internalServices.dto.Response.JobResponse;
import com.jimmodel.internalServices.dto.Response.OptionResponse;
import com.jimmodel.internalServices.dto.Response.OptionsResponse;
import com.jimmodel.internalServices.model.Event;
import com.jimmodel.internalServices.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/option")
public class OptionController {

    @Autowired
    OptionService optionService;

    @PostMapping
    public ResponseEntity<OptionResponse> createOption(@RequestBody OptionRequest optionRequest){
        Event createdOption = optionService.save(optionRequest.toEntity());
        OptionResponse responseBody = new OptionResponse(createdOption);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OptionResponse> updateOptionById(@PathVariable(name = "id") UUID id, @RequestBody OptionRequest optionRequest){
        Event updatedOption = optionService.saveById(id, optionRequest.toEntity());
        OptionResponse responseBody = new OptionResponse(updatedOption);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OptionResponse> getOptionById(@PathVariable(name = "id") UUID id){
        Event option = optionService.findById(id);
        OptionResponse responseBody = new OptionResponse(option);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<OptionsResponse> getOptions(
            @RequestParam(required = false, defaultValue = "0", name = "pageNumber") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "${data.page-size}", name = "pageSize") Integer pageSize,
            @RequestParam(required = false, defaultValue = "${data.option.sort-by}", name = "sortBy") String sortBy,
            @RequestParam(required = false, defaultValue = "${data.sort-dir}", name = "sortDir") String sortDir
    ){
        Page<Event> optionPage = optionService.findAll(pageNumber, pageSize, sortBy, sortDir);
        OptionsResponse responseBody = new OptionsResponse(optionPage);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/job/{id}")
    public  ResponseEntity<JobResponse> toJob(@PathVariable(name = "id") UUID id){
        Event job = optionService.toJob(id);
        JobResponse responseBody = new JobResponse(job);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOptionById(@PathVariable(name = "id") UUID  id){
        optionService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/search/{searchTerm}")
    public ResponseEntity<OptionsResponse> search(
            @PathVariable("searchTerm") String searchTerm,
            @RequestParam(required = false, defaultValue = "0", name = "pageNumber") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "${data.page-size}", name = "pageSize") Integer pageSize,
            @RequestParam(required = false, defaultValue = "${data.option.sort-by}", name = "sortBy") String sortBy,
            @RequestParam(required = false, defaultValue = "${data.sort-dir}", name = "sortDir") String sortDir
    ){
        Page<Event> optionPage = optionService.search(searchTerm, pageNumber, pageSize, sortBy, sortDir);
        OptionsResponse responseBody = new OptionsResponse(optionPage);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
