package com.financeDashboard.financeApp.dto;

import com.financeDashboard.financeApp.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class RegisterResponse {
    private UUID userId;
    private String name;
    private String email;
    private Role role;
}
