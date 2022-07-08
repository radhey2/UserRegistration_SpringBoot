package com.example.userregistration.Repository;

import com.example.userregistration.Modal.UserRegistrationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistrationData,Integer> {

//    @Query(value = "SELECT * FROM  user_detail where email=:email", nativeQuery = true)
//    public Optional<UserRegistrationData> findByEmail(String email);

    @Query(value = "select * from user_detail where email=?1 password = ?2", nativeQuery = true)
    Long findByPassword(String email, String password);

    //Optional<User> findByEmail(String email);

    // @Query(value = "select * from user where email=?1",nativeQuery = true)
    // Optional<User> getUserByEmail(String email);

    @Query(value = " select  * from user_detail id=?1", nativeQuery = true)
    UserRegistrationData getById(String id);

    Optional<UserRegistrationData> findByEmail(String email);
//    Optional<UserRegistrationData> getUserByEmail(String email);
}
