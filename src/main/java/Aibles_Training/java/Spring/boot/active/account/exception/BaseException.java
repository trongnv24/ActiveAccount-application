package Aibles_Training.java.Spring.boot.active.account.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private  String message;
    private  String id;
    private  String objectName;
    public void NotFoundException(String message, String id, String objectName) {
        this.id = id;
        this.objectName = objectName;
        this.message = message;
    }

}
