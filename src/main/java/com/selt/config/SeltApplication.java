package com.selt.config;

import com.selt.model.Role;
import com.selt.model.User;
import com.selt.model.UserRole;
import com.selt.repository.RoleRepo;
import com.selt.repository.UserRepo;
import com.selt.service.PrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
@ComponentScan("com.selt")
@EntityScan("com.selt.model")
@EnableJpaRepositories("com.selt.repository")
@Import(SecurityConfig.class)
@EnableScheduling
public class SeltApplication implements CommandLineRunner {

    @Autowired
    private RoleRepo roleRepository;
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private PasswordEncoder encoder;
    private PrinterService printerService;
//	private WindowsRepo windowsRepo;
//	private LaptopRepo laptopRepo;


    public static void main(String[] args) {
        SpringApplication.run(SeltApplication.class, args);
    }


        // execute every 10 seconds
        @Scheduled(cron = "0 0 9 ? * MON")
        public void writeSomething1() {
            printerService.getCounter();
        }



    @Override
    public void run(String... args) throws Exception {
//        while(LocalTime.now().getMinute()%2==0 && LocalTime.now().getSecond()==1){
//            System.out.println(LocalTime.now().getMinute());


//        // userService.deleteUser("ss");
        if (userRepository.findAll().size() == 0) {
            UserRole userRole = new UserRole();
            userRole.setRole(Role.ADMIN);
            userRole = roleRepository.save(userRole);
            UserRole userRole2 = new UserRole();
            userRole2.setRole(Role.USER);

            roleRepository.save(userRole2);

            User user = new User();
            user.setLogin("user");
            user.setPassword(encoder.encode("omg11thc"));
            user.setCreateDate(new Date());
            user.setRoles(Arrays.asList(userRole2));
            //user.setRole(userRole2);
            user.setEnabled(true);
            user.setFullname("User");
            userRepository.save(user);

            User admin = new User();
            admin.setLogin("admin");
            admin.setPassword(encoder.encode("omg11thc"));
            admin.setCreateDate(new Date());
            admin.setRoles(Arrays.asList(userRole));
            admin.setFullname("Admin");
            //admin.setRole(userRole);
            admin.setEnabled(true);
            userRepository.save(admin);
        }
    }
}
