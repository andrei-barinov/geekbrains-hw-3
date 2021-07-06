package ru.geekbrains.hw9.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductErrorResponse {
    private int status;
    private String message;
    private long timestamp;
}
