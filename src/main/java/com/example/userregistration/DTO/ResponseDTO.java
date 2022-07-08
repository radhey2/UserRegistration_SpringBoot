package com.example.userregistration.DTO;

import com.example.userregistration.Modal.UserRegistrationData;
import lombok.Data;

import java.util.List;

public @Data class ResponseDTO {
    public String message;
    public Object data;
    public String token;

    public ResponseDTO(String message, Object data, String token){
        this.message = message;
        this.data = data;
        this.token = token;
    }
}