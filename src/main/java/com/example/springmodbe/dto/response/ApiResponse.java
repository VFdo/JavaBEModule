package com.example.springmodbe.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResponse<T> {
    private int status;
    private String message;
    private T payload;

    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.payload = data;
    }
}
