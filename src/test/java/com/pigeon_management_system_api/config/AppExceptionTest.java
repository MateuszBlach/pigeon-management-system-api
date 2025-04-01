package com.pigeon_management_system_api.config;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class AppExceptionTest {

    @Test
    void testAppExceptionConstructorAndGetters() {
        String message = "Test Exception";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        AppException exception = new AppException(message, status);
        assertEquals(message, exception.getMessage());
        assertEquals(status, exception.getStatus());
    }
}