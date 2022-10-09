package ru.skilanov.spring.service.api;

public interface IOService {

    void printLocalizedMessage(String systemMessage);

    void printMessage(String systemMessage);

    String scanMessage();
}
