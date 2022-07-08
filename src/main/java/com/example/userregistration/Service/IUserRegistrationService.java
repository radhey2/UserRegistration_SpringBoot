package com.example.userregistration.Service;

import com.example.userregistration.DTO.LoginDTO;
import com.example.userregistration.DTO.ResponseDTO;
import com.example.userregistration.DTO.UserRegistrationDTO;
import com.example.userregistration.Modal.UserRegistrationData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserRegistrationService {
    List<UserRegistrationData> gatAllUser();

    UserRegistrationData getUserByid(int id);

    ResponseEntity<ResponseDTO> createAccount(UserRegistrationDTO userRegistrationDTO);

    ResponseEntity<ResponseDTO> verifyUser(String token);

    ResponseEntity<ResponseDTO> loginUser(LoginDTO loginDTO);
}
