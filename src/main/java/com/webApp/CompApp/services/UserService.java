package com.webApp.CompApp.services;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.webApp.CompApp.models.Station;
import com.webApp.CompApp.models.User;
import com.webApp.CompApp.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User GetCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName(); 

        return findByLogin(login);
        
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public List<User> findByStationId(Long stationId) {
        return userRepository.findByStation_Id(stationId);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void registerUser(User user) {
        // Хэшируем пароль перед сохранением. Spring Security автоматически использует PasswordEncoder для сравнения введённого пароля с хранимым хэшем.
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }

    public void changeData(User user, String name, String surname, String login, String password, String role ) {
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);
        // Хэшируем пароль перед сохранением. Spring Security автоматически использует PasswordEncoder для сравнения введённого пароля с хранимым хэшем.
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setRole(role);

        userRepository.save(user);
    }



}