package ru.skilanov.spring.exceptions;

public class FileDoesNotExistException extends RuntimeException{
    public FileDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
