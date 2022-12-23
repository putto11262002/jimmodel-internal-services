package com.jimmodel.services.dto.Response;

import lombok.*;

import java.time.Instant;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ErrorResponse {
    private String messages;
    private String errorMessageKey;
    private Integer errorCode;
    private Instant timestamp;
    private String url;
    private String method;
}
