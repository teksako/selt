package com.selt.config;

import com.selt.model.Role;
import com.selt.model.User;
import com.selt.model.UserRole;
import com.selt.repository.RoleRepo;
import com.selt.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
@ComponentScan("com.selt")
@EntityScan("com.selt.model")
@EnableJpaRepositories("com.selt.repository")
@Import(SecurityConfig.class)
public class SeltApplication implements CommandLineRunner {

    @Autowired
    private RoleRepo roleRepository;
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private PasswordEncoder encoder;
//	private WindowsRepo windowsRepo;
//	private LaptopRepo laptopRepo;


    public static void main(String[] args) {
        SpringApplication.run(SeltApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        // userService.deleteUser("ss");
               if (userRepository.findAll().size() == 0) {
                   UserRole userRole = new UserRole();
                   userRole.setRole(Role.ADMIN);
                   userRole = roleRepository.save(userRole);
                   UserRole userRole2 = new UserRole();
                   userRole2.setRole(Role.USER);
                   roleRepository.save(userRole2);


			User user = new User();
			user.setUsername("user");
			user.setPassword(encoder.encode("omg11thc"));
			user.setCreateDate(new Date());
			user.setRoles(Arrays.asList(userRole2));
			//user.setRole(userRole2);
			user.setEnabled(true);
			userRepository.save(user);

			User admin = new User();
			admin.setUsername("admin");
			admin.setPassword(encoder.encode("omg11thc"));
			admin.setCreateDate(new Date());
			admin.setRoles(Arrays.asList(userRole));

			//admin.setRole(userRole);
			admin.setEnabled(true);
			userRepository.save(admin);


		}


        }

    }
