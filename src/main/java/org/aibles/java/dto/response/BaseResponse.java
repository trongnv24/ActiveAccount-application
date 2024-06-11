package org.aibles.java.dto.response;

public class BaseResponse<T> {
    private String code;
    private long timestamp;
    private T data;

    public BaseResponse(String code, long timestamp, T data) {
        this.code=code;
        this.timestamp=timestamp;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code='" + getCode() + '\'' +
                ", timestamp=" + getTimestamp() +
                ", data=" + getData() +
                '}';
    }
}
