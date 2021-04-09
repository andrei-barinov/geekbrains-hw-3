package ru.geekbrains.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.geekbrains.services.RoleService;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class PersonDTO {
    private final RoleService roleService;

    private String personName;

    private String role;

    private String login;

    private String password;

}
