package com.animals_back.services;


import com.animals_back.entities.Role;
import com.animals_back.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Сервис для обработки различных действий со ролями
 */
@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    /**
     * Метод находит роль ROLE_USER в базы данных
     * @return - Role - ROLE_USER
     */
    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").orElseThrow(RuntimeException::new);
    }
}
