package ru.skilanov.spring.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {
    private final Scanner scanner;

    public IOServiceImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void printMessage(String systemMessage) {
        System.out.println(systemMessage);
    }

    @Override
    public String scanMessage(String userMessage) {
        System.out.println(userMessage);
        return scanner.next();
    }
}
