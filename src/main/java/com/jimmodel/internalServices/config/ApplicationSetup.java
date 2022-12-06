package com.jimmodel.internalServices.config;

import com.jimmodel.internalServices.service.ModelService;
import com.jimmodel.internalServices.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class ApplicationSetup implements CommandLineRunner {

    @Autowired
    private StorageService storageService;

    @Autowired
    private ModelService modelService;
    @Override
    public void run(String... args) throws Exception {
        storageService.init();

        // load default data



    }
}
