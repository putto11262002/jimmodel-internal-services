package com.jimmodel.internalServices.config;


import lombok.Getter;
import lombok.Setter;
;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Component
@Getter
@Setter
public class StorageConfig {
    public static String ROOT_DIR = "upload";

    public static Set<String> VALID_IMAGE_FORMAT = new HashSet<>(Arrays.asList("image/png", "image/jpeg", "image/jpg"));
}
