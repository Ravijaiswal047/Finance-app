package com.financeDashboard.financeApp.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class DashboardSummaryDTO {
    private double totalIncome;
    private double totalExpense;
    private double netBalance;
}
