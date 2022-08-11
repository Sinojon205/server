package com.example.server;

import com.example.server.enumeration.Status;
import com.example.server.model.Role;
import com.example.server.model.Server;
import com.example.server.model.User;
import com.example.server.repo.ServerRepo;
import com.example.server.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(ServerRepo serverRepo, UserService userService) {
        return args -> {
            serverRepo.save(new Server(null, "192.168.88.160", "Ubuntu Linux", "16 GB", "Personal PC", "http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
            serverRepo.save(new Server(null, "192.168.88.177", "Ubuntu Linux", "16 GB", "Personal PC", "http://localhost:8080/server/image/server2.png", Status.SERVER_DOWN));
            serverRepo.save(new Server(null, "192.168.88.191", "Windows 11", "16 GB", "Personal PC", "http://localhost:8080/server/image/server3.png", Status.SERVER_DOWN));
            serverRepo.save(new Server(null, "192.168.88.225", "Windows 10", "16 GB", "Personal PC", "http://localhost:8080/server/image/server4.png", Status.SERVER_UP));
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
            userService.saveUser(new User(null, "John Travolta", "john", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Will Smith", "will", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Jim Carry", "jim", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Mila Mala", "mila", "1234", new ArrayList<>()));
            userService.addRoleToUser("john", "ROLE_USER");
            userService.addRoleToUser("john", "ROLE_MANAGER");
            userService.addRoleToUser("will", "ROLE_USER");
            userService.addRoleToUser("jim", "ROLE_ADMIN");
            userService.addRoleToUser("mila", "ROLE_USER");
            userService.addRoleToUser("mila", "ROLE_ADMIN");
            userService.addRoleToUser("mila", "ROLE_SUPER_ADMIN");
        };
    }




}
