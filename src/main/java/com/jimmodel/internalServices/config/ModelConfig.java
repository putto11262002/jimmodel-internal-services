package com.jimmodel.internalServices.config;

import com.jimmodel.internalServices.dto.Response.ErrorResponse;
import com.jimmodel.internalServices.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.List;

public class ModelConfig {
    public static final String DEFAULT_PAGE_SIZE = "5";
    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_SORT_BY = "firstName";
    public static final String DEFAULT_SORT_DIR = "asc";

    public static final Integer COMP_CARD_IMAGES_LIMIT = 10;

    public static final String MODEL_DIR = "model";

    public static final String COMP_CARD_IMAGE_PREFIX = "comp-card";

    public static final String PROFILE_IMAGE_PREFIX = "profile";

}
