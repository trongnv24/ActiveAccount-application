package Aibles_Training.java.Spring.boot.active.account.dto.response;

import lombok.Getter;


@Getter
public class OtpResponse {
    private String code;
    private long timestamp;
    private Object data;

    public OtpResponse(String code, long timestamp, Object data) {
        this.code = code;
        this.timestamp = timestamp;
        this.data = data;
    }
}
