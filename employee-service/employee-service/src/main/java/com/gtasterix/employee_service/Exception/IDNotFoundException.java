package com.gtasterix.employee_service.Exception;

public class IDNotFoundException extends RuntimeException
{
    public IDNotFoundException(String message) {
        super(message);
    }
}
