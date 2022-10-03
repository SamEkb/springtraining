package ru.skilanov.spring.service;

public interface IOService {

    void printLocalizedMessage(String systemMessage);

    void printMessage(String systemMessage);

    String scanMessage();
}
