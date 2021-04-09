package ru.geekbrains.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.entity.Role;
import ru.geekbrains.exception.PersonNotFoundException;
import ru.geekbrains.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findRoleByName(String name){
        return roleRepository.findRoleByName(name).orElseThrow(() -> new PersonNotFoundException(
                String.format("Не найден человек с названием %s", name)));
    }
}
