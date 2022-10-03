package ru.skilanov.spring.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {
    private final Scanner scanner;
    private final LocalizationService localizationService;

    public IOServiceImpl(LocalizationService localizationService) {
        this.scanner = new Scanner(System.in);
        this.localizationService = localizationService;
    }

    @Override
    public void printLocalizedMessage(String systemMessage) {
        System.out.println(localizationService.getLocalizedMessage(systemMessage));
    }

    @Override
    public void printMessage(String systemMessage) {
        System.out.println(systemMessage);
    }

    @Override
    public String scanMessage() {
        return scanner.next();
    }
}
