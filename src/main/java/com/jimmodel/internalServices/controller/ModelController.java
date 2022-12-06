package com.jimmodel.internalServices.controller;

import com.jimmodel.internalServices.model.Model;
import com.jimmodel.internalServices.model.Image;
import com.jimmodel.internalServices.dto.Request.ModelRequest;
import com.jimmodel.internalServices.dto.Response.ImageResponse;
import com.jimmodel.internalServices.dto.Response.ModelResponse;
import com.jimmodel.internalServices.dto.Response.ModelsResponse;
import com.jimmodel.internalServices.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@RestController
@RequestMapping("/v1/model")
public class ModelController {
    @Autowired
    private ModelService modelService;

    @PostMapping
    public ResponseEntity<ModelResponse> createModel(@RequestBody ModelRequest modelRequest) {
        Model createdModel = modelService.save(modelRequest.toEntity());
        ModelResponse responseBody = new ModelResponse(createdModel);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelResponse> getModelById(@PathVariable UUID id) {
        Model model = modelService.findById(id);
        System.out.println(model);
        ModelResponse responseBody = new ModelResponse(model);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ModelsResponse> getModels(
            @RequestParam(required = false, defaultValue = "0", name = "pageNumber") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "${data.page-size}", name = "pageSize") Integer pageSize,
            @RequestParam(required = false, defaultValue = "${data.model.sort-by}", name = "sortBy") String sortBy,
            @RequestParam(required = false, defaultValue = "${data.sort-dir}", name = "sortDir") String sortDir
    ) {
        Page<Model> modelPages = modelService.findAll(pageNumber, pageSize, sortBy, sortDir);
        ModelsResponse responseBody = new ModelsResponse(modelPages);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelResponse> updateModelById(@PathVariable(name = "id") UUID id, @RequestBody ModelRequest modelRequest){
        Model updatedModel = modelService.saveById(id, modelRequest.toEntity());
        ModelResponse responseBody = new ModelResponse(updatedModel);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteModelById(@PathVariable(name = "id") UUID id){
        modelService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/search/{searchTerm}")
    public ResponseEntity<ModelsResponse> searchModel(
            @PathVariable(name = "searchTerm") String searchTerm,
            @RequestParam(required = false, defaultValue = "0", name = "pageNumber") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "${data.page-size}", name = "pageSize") Integer pageSize,
            @RequestParam(required = false, defaultValue = "${data.model.sort-by}", name = "sortBy") String sortBy,
            @RequestParam(required = false, defaultValue = "${data.sort-dir}", name = "sortDir") String sortDir
    ){
        Page<Model> modelPage =  modelService.search(searchTerm, pageNumber, pageSize, sortBy, sortDir);
        ModelsResponse responseBody = new ModelsResponse(modelPage);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PostMapping("/image/profile/{id}")
    public ResponseEntity<ImageResponse> saveModelProfileImageByModelId(@PathVariable(name = "id") UUID id, @RequestParam(name = "image") MultipartFile file){
        Image image = modelService.saveProfileImageById(id, file);
        ImageResponse responseBody = new ImageResponse(image);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PostMapping("/image/comp-card/{id}")
    public ResponseEntity<ImageResponse> saveModelCompCardImageByModelId(@PathVariable(name = "id") UUID id, @RequestParam(name = "image") MultipartFile file){
        Image image = modelService.saveCompCardImageById(id, file);
        ImageResponse responseBody = new ImageResponse(image);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @DeleteMapping("image/profile/{id}")
    public ResponseEntity deleteModelProfileImageByModelId(@PathVariable(name = "id") UUID id){
        modelService.deleteProfileImageById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("image/comp-card/{id}")
    public ResponseEntity deleteModelCompCardImageByImageId(@PathVariable(name = "id") UUID id){
        modelService.deleteCompCardImageByImageId(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/image/{id}/{fileName}")
    public ResponseEntity<Resource> getModelImage(@PathVariable(name = "id") UUID id, @PathVariable(name = "fileName") String fileName){
        Resource image = modelService.getImage(id, fileName);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.parseMediaType(String.format("image/%s", fileName.substring(fileName.lastIndexOf(".")))));
        return new ResponseEntity<>(image,header, HttpStatus.OK);
    }

}
