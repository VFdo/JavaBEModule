package com.example.springmodbe.controller;

import com.example.springmodbe.dto.request.UserDto;
import com.example.springmodbe.dto.response.ApiResponse;
import com.example.springmodbe.entity.User;
import com.example.springmodbe.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

//    @PostMapping(value = "/login")
//    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
//        String token = JwtHelper.generateToken(request.email());
//        return ResponseEntity.ok(new LoginResponse(request.email(), token));
//    }

    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<ApiResponse<User>> setUser(@RequestBody UserDto userDto){
        User user = userService.saveUser(userDto);
        return ResponseEntity.ok(new ApiResponse<>(200, "Success", user));
    }
    @GetMapping("/users")
    public ResponseEntity<ApiResponse<Page<User>>> getUsers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<User> users = userService.getAllUsers(pageNumber, pageSize);
        return ResponseEntity.ok(new ApiResponse<>(200, "Success", users));
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse<User>> getUser(@PathVariable String id){
        User existingUser = userService.getUser(id);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Success",
                existingUser
        ));
    }
    @PutMapping("user/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable String id, @RequestBody UserDto userDto){
        User updatedUser = userService.updateUser(id,userDto);
        return ResponseEntity.ok(new ApiResponse(
                200,
                "Success",
                updatedUser
        ));
    }
    @DeleteMapping("user/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
