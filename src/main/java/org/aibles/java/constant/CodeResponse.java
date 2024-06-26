package org.aibles.java.constant;

public enum CodeResponse {
    ACCOUNT_NOT_ACTIVATED("account_not_activated"),
    SUCCESS("success");

    private final String value;

    CodeResponse(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
