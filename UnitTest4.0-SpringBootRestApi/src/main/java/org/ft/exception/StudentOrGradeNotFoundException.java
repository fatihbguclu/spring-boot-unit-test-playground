package org.ft.exception;

public class StudentOrGradeNotFoundException extends RuntimeException {

    public StudentOrGradeNotFoundException(String message) {
        super(message);
    }

    public StudentOrGradeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentOrGradeNotFoundException(Throwable cause) {
        super(cause);
    }
}
