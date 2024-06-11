package org.aibles.java.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;
@Getter
@Setter
public class NotFoundException extends RuntimeException {
    private String code;
    private Long timestamp;
    private Map<String, String> error;

    public NotFoundException(Map<String, String> error) {
        this.code = "not_found";
        this.timestamp = Instant.now().toEpochMilli();
        this.error = error;
    }

}
