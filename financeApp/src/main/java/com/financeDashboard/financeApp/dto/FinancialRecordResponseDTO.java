package com.financeDashboard.financeApp.dto;

import com.financeDashboard.financeApp.enums.RecordType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class FinancialRecordResponseDTO {

    private Long id;
    private Double amount;
    private RecordType type;
    private String category;
    private LocalDate date;
    private String note;
    private UUID userId;
}
