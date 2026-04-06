package com.financeDashboard.financeApp.dto;

import com.financeDashboard.financeApp.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupDto {
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Role role;

}
