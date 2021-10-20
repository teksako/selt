package com.selt.controler;

import com.selt.model.User;
import com.selt.model.UserRole;
import com.selt.repository.RoleRepo;
import com.selt.service.RoleServis;
import com.selt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RoleServis roleServis;
    private final RoleRepo roleRepo;


    @GetMapping({"/User"})
    public String userPage(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        List<UserRole> roleList = roleServis.findAll();
        model.addAttribute("roleList", roleList);
        model.addAttribute("username", userService.findUserByUsername().getFullname());
        return "User";
    }

    @PostMapping({"/addUser"})
    public String saveUser(User user, String password, Model model) {
        model.addAttribute("password", password);

        //userService.save(user,password);
        model.addAttribute("info", userService.save(user, password));
        userPage(model);
        return "User";
    }

    @PostMapping({"/changePassword"})
    public String changePasword(String password, Model model) {
        model.addAttribute("password", password);

        userService.changePassword(password);
        userPage(model);
        return "User";
    }

    @PostMapping({"/resetPassword"})
    public String resetPasword(User user, String password, Model model) {
        model.addAttribute("password", password);

        userService.resetPasswordByAdmin(user,password);
        userPage(model);
        return "User";
    }
}
