package com.example.springmodbe.service;

import com.example.springmodbe.dto.request.UserDto;
import com.example.springmodbe.entity.User;
import com.example.springmodbe.exception.AlreadyExistsException;
import com.example.springmodbe.exception.ResourceNotFoundException;
import com.example.springmodbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Page<User> getAllUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return userRepository.findAll(pageable);
    }
    public User getUser(String userId) {
        User user = userRepository.findByRefId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));
        return user;
    }
    public User updateUser(String userId, UserDto userDto) {
        User user = userRepository.findByRefId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));
        user.setUsername(userDto.userName());
        user.setPassword(userDto.password());
        user.setEmail(userDto.email());
        user.setRole(userDto.role());
        return userRepository.save(user);
    }
    public void deleteUser(String userId) {
        User user = userRepository.findByRefId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));
        userRepository.delete(user);
    }

    public User saveUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.email()).isPresent()) {
            throw new AlreadyExistsException("Email is already in use.");
        }
        if (userRepository.findByUsername(userDto.userName()).isPresent()) {
            throw new AlreadyExistsException("Username is already taken.");
        }

        User user = new User(
                userDto.userName(),
                passwordEncoder.encode(userDto.password()),
                userDto.email(),
                userDto.role()
        );
        return userRepository.save(user);
    }
}
