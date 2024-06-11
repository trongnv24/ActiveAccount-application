package org.aibles.java.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse<T> {
    private String code;
    private long timestamps;
    private T error;

    public ErrorResponse(String code, long timestamps, T error) {
        this.code = code;
        this.timestamps = timestamps;
        this.error = error;

    }
}
