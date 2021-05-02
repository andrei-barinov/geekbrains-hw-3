package ru.geekbrains.entity;

import lombok.Data;

@Data
public class ErrorEntity {
    private String message;
    private int status;

    public ErrorEntity(String message) {
        this.message = message;
    }
    public ErrorEntity(String message, Integer status) {
        this.message = message;
        this.status = status;
    }
}
