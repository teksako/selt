package com.selt.controler;

import com.selt.model.Role;
import com.selt.model.User;
import com.selt.model.UserRole;
import com.selt.repository.RoleRepo;
import com.selt.service.RoleServis;
import com.selt.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RoleServis roleServis;
    private final RoleRepo roleRepo;


    @GetMapping({"/addUser"})
    public String userPage(Model model){
       model.addAttribute("user",new User());
        List<UserRole> roleList=roleServis.findAll();
        model.addAttribute("roleList", roleList);
        model.addAttribute("username",  userService.findUserByUsername().getFullname());
        return "/addUser";
    }

    @PostMapping({"/addUser"})
    public String saveUser(User user, String password, Model model ){
        model.addAttribute("password", password);

        userService.save(user,password);
        userPage(model);
        return "/index";
    }
}
