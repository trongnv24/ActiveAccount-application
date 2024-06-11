package org.aibles.java.dto.response;

public class ActiveAccountResponse  {

    private Long id;
    private String username;
    private String message;

    public ActiveAccountResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "ActiveAccountResponse{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", message='" + getMessage() + '\'';
    }
}
