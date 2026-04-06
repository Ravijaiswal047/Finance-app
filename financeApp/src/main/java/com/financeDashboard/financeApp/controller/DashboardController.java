package com.financeDashboard.financeApp.controller;

import com.financeDashboard.financeApp.dto.DashboardSummaryDTO;
import com.financeDashboard.financeApp.service.FinancialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final FinancialService financialService;

    @GetMapping("/summary")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    public ResponseEntity<DashboardSummaryDTO> getSummary() {

        return ResponseEntity.ok(financialService.getDashboardSummary());
    }
}
