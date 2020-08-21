package com.goganesh.warehouse.exception;

public class PathIsNotAccepted extends RuntimeException {
    public PathIsNotAccepted(String message) {
        super(message);
    }

    public PathIsNotAccepted(String message, Throwable cause) {
        super(message, cause);
    }
}
