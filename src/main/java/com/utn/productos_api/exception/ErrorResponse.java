package com.utn.productos_api.exception;

import lombok.Data;
import java.time.LocalDateTime;
import lombok.Builder;



@Data
@Builder
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;
}