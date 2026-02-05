package com.deveyk.northwind.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String type;
    private int status;
    private String error;
    private String message;
    private Map<String, String> details;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String path;

}
