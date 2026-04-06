package com.financeDashboard.financeApp.service;

import com.financeDashboard.financeApp.dto.ApiResponse;
import com.financeDashboard.financeApp.dto.LoginDto;
import com.financeDashboard.financeApp.dto.RegisterResponse;
import com.financeDashboard.financeApp.dto.SignupDto;
import com.financeDashboard.financeApp.entity.User;
import com.financeDashboard.financeApp.enums.Role;
import com.financeDashboard.financeApp.repository.UserRepository;
import com.financeDashboard.financeApp.util.JwtUtil;
import com.financeDashboard.financeApp.util.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public RegisterResponse register(@RequestBody SignupDto request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exist");
        }
        Role role = request.getRole() != null ? request.getRole() : Role.VIEWER;
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // (plain for assignment)
                .role(request.getRole())
                .build();
        user = userRepository.save(user);


        return RegisterResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();


    }


    public ResponseEntity<?> login(LoginDto request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() ->
                new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Credentials");
        }

        return  ResponseEntity.ok(ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .message("Login successful")
                .data(Map.of(
                        "token", token,
                        "role", user.getRole()
                ))
                .build()
        );
    }
}
