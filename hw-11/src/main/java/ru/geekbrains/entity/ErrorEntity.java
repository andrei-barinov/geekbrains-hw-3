package ru.geekbrains.entity;

import lombok.Data;

@Data
public class ErrorEntity {
    private String message;

    public ErrorEntity(String message) {
        this.message = message;
    }
}
