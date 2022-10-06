package ru.skilanov.spring.service.implementation;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.skilanov.spring.config.AppConfig;
import ru.skilanov.spring.service.api.LocalizationService;

@Service
public class LocalizationServiceImpl implements LocalizationService {
    private final MessageSource messageSource;
    private final AppConfig appConfig;

    public LocalizationServiceImpl(MessageSource messageSource, AppConfig appConfig) {
        this.messageSource = messageSource;
        this.appConfig = appConfig;
    }

    @Override
    public String getLocalizedMessage(String message) {
        return messageSource.getMessage(message, null, appConfig.getLocale());
    }
}
