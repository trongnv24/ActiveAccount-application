package Aibles_Training.java.Spring.boot.active.account.dto.request;

public class ActiveAccountRequest {

    private String username;
    public ActiveAccountRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ActiveAccountRequest{" +
                "username='" + getUsername() + '\'' +
                '}';
    }
}
