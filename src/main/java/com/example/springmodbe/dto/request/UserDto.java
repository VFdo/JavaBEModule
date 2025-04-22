package com.example.springmodbe.dto.request;

import com.example.springmodbe.entity.Role;

import java.util.List;

public record UserDto(String userName,
                      String password,
                      String email,
                      Role role) {
}
