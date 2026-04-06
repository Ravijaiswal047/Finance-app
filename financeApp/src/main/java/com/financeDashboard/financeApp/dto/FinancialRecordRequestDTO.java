package com.financeDashboard.financeApp.dto;

import com.financeDashboard.financeApp.enums.RecordType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class FinancialRecordRequestDTO {
    @NotNull
    private Double amount;

    @NotNull
    private RecordType type;

    @NotBlank
    private String category;

    @NotNull
    private LocalDate date;

    private String note;

    @NotNull
    private UUID userId;
}
