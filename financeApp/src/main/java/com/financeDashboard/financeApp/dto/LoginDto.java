package com.financeDashboard.financeApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDto {

    @NotBlank
    private String email;

    @NotNull
    private String password;

}
