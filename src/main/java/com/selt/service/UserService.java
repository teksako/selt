package com.selt.service;

import com.selt.model.User;
import com.selt.repository.UserRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Data
@Transactional
public class UserService {


    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

//    public List<User> findAll() {
//        return userRepo.findAllByUsername("admin");
//    }

    public void delete(User user) {
        userRepo.delete(user);
    }

    public void save(User user, String password) {
        user.setCreateDate(new Date());
        user.setPassword(passwordEncoder.encode(password));
        userRepo.save(user);
    }

    private UserDetails userDetailsService() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public String actualLoginUser() {
        return userDetailsService().getUsername();
    }

    public User findUserByUsername() {
        return userRepo.findUserByUsername(actualLoginUser());
    }
}
