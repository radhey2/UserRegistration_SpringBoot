package com.example.userregistration.Modal;

import com.example.userregistration.DTO.UserRegistrationDTO;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "UserDetail")
public class UserRegistrationData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid", nullable = false)
    private int id;

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    public boolean verified;


   public UserRegistrationData(UserRegistrationDTO userRegistrationDTO){
       this.firstname = userRegistrationDTO.firstname;
       this.lastname = userRegistrationDTO.lastname;
       this.email = userRegistrationDTO.email;
       this.password = userRegistrationDTO.password;
       this.verified = userRegistrationDTO.verified;

   }

    public UserRegistrationData() {

    }
}
