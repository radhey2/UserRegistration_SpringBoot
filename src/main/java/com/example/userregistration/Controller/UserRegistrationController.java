package com.example.userregistration.Controller;

import com.example.userregistration.DTO.LoginDTO;
import com.example.userregistration.DTO.ResponseDTO;
import com.example.userregistration.DTO.UserRegistrationDTO;
import com.example.userregistration.Modal.UserRegistrationData;
import com.example.userregistration.Service.IUserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping(value = "/UserRegistration")
public class UserRegistrationController {

    @Autowired
    private IUserRegistrationService userRegistrationService;

    @GetMapping(value = {"","/","getAll"})
    public ResponseEntity<ResponseDTO> getAllUser(){
        List<UserRegistrationData> userDataList = userRegistrationService.gatAllUser();
        ResponseDTO responseDTO = new ResponseDTO("Get Call For Success",userDataList,null);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

   @GetMapping(value = "/getbyid/{id}")
    public ResponseEntity<ResponseDTO> getUserByid(@PathVariable("id") int id){
        UserRegistrationData userRegistrationData = userRegistrationService.getUserByid(id);
        ResponseDTO responseDTO = new ResponseDTO("Get Call for User Id :",userRegistrationData,null);
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
   }

   @PostMapping(value = "/create")
    public ResponseEntity<ResponseDTO> adduser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO){
        return userRegistrationService.createAccount(userRegistrationDTO);
   }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDTO){
        return userRegistrationService.loginUser(loginDTO);

    }
    @GetMapping("/verify/{token}")
    public ResponseEntity<ResponseDTO> verifyUser(@PathVariable ("token") String token) {
        return userRegistrationService.verifyUser(token);
    }
}
