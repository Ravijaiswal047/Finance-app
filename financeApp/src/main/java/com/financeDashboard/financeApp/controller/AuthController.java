package com.financeDashboard.financeApp.controller;

import com.financeDashboard.financeApp.dto.LoginDto;
import com.financeDashboard.financeApp.dto.SignupDto;
import com.financeDashboard.financeApp.repository.UserRepository;
import com.financeDashboard.financeApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<?> RegisterUser(@RequestBody SignupDto user){
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> LoginUser(@RequestBody LoginDto request){
        return ResponseEntity.ok(userService.login(request));
    }

}
