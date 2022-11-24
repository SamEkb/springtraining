package ru.skilanov.spring.dto;

public class AuthorDto {
    private long id;

    private String name;

    public AuthorDto() {
    }

    public AuthorDto(String name) {
        this.name = name;
    }

    public AuthorDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
