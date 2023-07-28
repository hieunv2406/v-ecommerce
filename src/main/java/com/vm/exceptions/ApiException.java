package com.vm.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * @author TuLa
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;
}
