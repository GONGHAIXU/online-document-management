package com.example.onlinedocumentsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  com.example.onlinedocumentsystem.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public List<User> findByPhoneNumber(String phoneNumber);
    public List<User> findByUsername(String username);
    public List<User> findByMail (String mail);
    public List<User> findByMailAndPassword (String mail,String password);
    public List<User> findByPhoneNumberAndPassword (String phoneNumber,String password);
    public List<User> findByUsernameAndPassword (String username,String password);
    public List<User> findByActivationCode(int activationCode);
}
