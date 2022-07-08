package com.example.userregistration.DTO;

import lombok.Data;

@Data
public class LoginDTO {
    public String email;
    public String password;

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginDTO() {
    }


}
