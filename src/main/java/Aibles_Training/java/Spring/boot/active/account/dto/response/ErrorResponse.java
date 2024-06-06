package Aibles_Training.java.Spring.boot.active.account.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String code;
    private long timestamps;
    private ErrorDetail error;

    public ErrorResponse(String code, long timestamps, ErrorDetail error) {
        this.code = code;
        this.timestamps = timestamps;
        this.error = error;

    }
}
