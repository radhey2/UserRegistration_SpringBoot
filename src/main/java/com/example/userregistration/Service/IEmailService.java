package com.example.userregistration.Service;

import com.example.userregistration.DTO.ResponseDTO;
import com.example.userregistration.Modal.Email;
import org.springframework.http.ResponseEntity;

public interface IEmailService {
   public ResponseEntity<ResponseDTO> sendMail(Email email);
   public String getLink(String token);
}
