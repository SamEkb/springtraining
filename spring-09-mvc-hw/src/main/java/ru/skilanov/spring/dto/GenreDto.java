package ru.skilanov.spring.dto;

public class GenreDto {
    private long id;

    private String name;

    public GenreDto() {
    }

    public GenreDto(String name) {
        this.name = name;
    }

    public GenreDto(long id, String name) {
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
