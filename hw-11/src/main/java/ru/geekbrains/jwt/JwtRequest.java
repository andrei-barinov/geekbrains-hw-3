package ru.geekbrains.jwt;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
