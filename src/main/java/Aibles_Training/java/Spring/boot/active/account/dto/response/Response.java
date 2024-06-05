package Aibles_Training.java.Spring.boot.active.account.dto.response;

public class Response {
    private String code;
    private long timestamp;
    private Object data;

    public Response(String code, long timestamp, Object data) {
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

    public void setData(Object data) {
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
