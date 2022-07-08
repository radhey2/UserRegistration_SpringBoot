package com.example.userregistration.DTO;

public class UserRegistrationDTO {
    public String firstname;
    public String lastname;
    public String email;
    public String password;
    public boolean verified;

    public UserRegistrationDTO(String firstname, String lastname,String email,String password){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.verified = false;
    }
}
