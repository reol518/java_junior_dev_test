package ru.nsu.error;

public class Error {
    public Error(String message) {
        this.message = message;
    }

    private final String type = "error";
    private String message;

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
