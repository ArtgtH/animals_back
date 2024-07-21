package com.animals_back.services;


import com.animals_back.DTO.RegistrationUserDTO;
import com.animals_back.entities.User;
import com.animals_back.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис для работы с пользователями и управления их данными.
 * Реализует интерфейс UserDetailsService для интеграции с Spring Security.
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    /**
     * Устанавливает репозиторий пользователей.
     *
     * @param userRepository репозиторий для работы с пользователями.
     */
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Устанавливает сервис кодирования паролей.
     *
     * @param passwordEncoder сервис для кодирования паролей.
     */
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Устанавливает сервис ролей.
     *
     * @param roleService сервис для работы с ролями.
     */
    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Ищет пользователя по его имени пользователя.
     *
     * @param username имя пользователя.
     * @return объект Optional с пользователем, если он найден.
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Загружает пользователя по его имени пользователя.
     * Реализация метода из интерфейса UserDetailsService.
     *
     * @param username имя пользователя.
     * @return объект UserDetails с информацией о пользователе.
     * @throws UsernameNotFoundException если пользователь не найден.
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
        );
    }

    /**
     * Создает нового пользователя.
     *
     * @param registrationUserDTO объект DTO с данными для регистрации пользователя.
     * @return созданный пользователь.
     */
    public User createUser(RegistrationUserDTO registrationUserDTO) {
        User user = new User();
        user.setEmail(registrationUserDTO.getEmail());
        user.setUsername(registrationUserDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registrationUserDTO.getPassword()));
        user.setRoles(List.of(roleService.getUserRole()));
        return userRepository.save(user);
    }
}
