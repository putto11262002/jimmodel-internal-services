package com.jimmodel.internalServices.config;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

public class ApplicationInit {
    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
