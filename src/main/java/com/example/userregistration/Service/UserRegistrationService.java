package com.example.userregistration.Service;

import com.example.userregistration.DTO.LoginDTO;
import com.example.userregistration.DTO.ResponseDTO;
import com.example.userregistration.DTO.UserRegistrationDTO;
import com.example.userregistration.Excepton.UserRegistrationException;
import com.example.userregistration.JWT.JwtTokenUtil;
import com.example.userregistration.Modal.Email;
import com.example.userregistration.Modal.UserRegistrationData;
import com.example.userregistration.Repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserRegistrationService implements IUserRegistrationService{

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private IEmailService emailService;

    @Override
    public List<UserRegistrationData> gatAllUser() {
        return userRegistrationRepository.findAll();
    }

    @Override
    public UserRegistrationData getUserByid(int id) {
        return userRegistrationRepository.findById(id).orElseThrow(()->new UserRegistrationException("User is Not Found"));
    }

    @Override
    public ResponseEntity<ResponseDTO> createAccount(UserRegistrationDTO userRegistrationDTO) {

        UserRegistrationData userRegistrationData= userRegistrationRepository.save(new UserRegistrationData(userRegistrationDTO));

        String token = JwtTokenUtil.createToken(Long.valueOf(userRegistrationData.getId()));
        Email email = new Email(userRegistrationData.getEmail()," user is registered",userRegistrationData.getFirstname() + "=>" + emailService.getLink(token));
        emailService.sendMail(email);

        ResponseDTO responseDto = new ResponseDTO("User is created", userRegistrationData, token);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<ResponseDTO> verifyUser(String token) {
        Optional<UserRegistrationData> user=userRegistrationRepository.findById(Math.toIntExact(jwtTokenUtil.decodeToken(token)));
        if (user.isEmpty()) {
            ResponseDTO responseDTO = new ResponseDTO("ERROR: Invalid token", null, token);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
        }
        user.get().setVerified(true);
        userRegistrationRepository.save(user.get());
        ResponseDTO responseDTO = new ResponseDTO(" The user has been verified ", user, token);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO> loginUser(LoginDTO loginDTO) {
        Optional<UserRegistrationData> user=userRegistrationRepository.findByEmail(loginDTO.getEmail());
        boolean password=user.get().getPassword().equals(loginDTO.getPassword());
        if(password=false){
            ResponseDTO responseDto=new ResponseDTO("login failed",null,null);
            return new ResponseEntity<ResponseDTO>(responseDto,HttpStatus.UNAUTHORIZED);
        }
        else{
            ResponseDTO responseDto=new ResponseDTO(" LOgin Sucessfully",user,null);
            return new ResponseEntity<>(responseDto,HttpStatus.OK);
        }

    }
}
