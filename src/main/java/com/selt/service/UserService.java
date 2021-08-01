package com.selt.service;

import com.selt.model.User;
import com.selt.repository.UserRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Data
@Transactional
public class UserService {


    private UserRepo userRepo;

//    public List<User> findAll() {
//        return userRepo.findAllByUsername("admin");
//    }

}
