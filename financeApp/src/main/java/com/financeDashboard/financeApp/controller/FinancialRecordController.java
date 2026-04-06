package com.financeDashboard.financeApp.controller;

import com.financeDashboard.financeApp.dto.FinancialRecordRequestDTO;
import com.financeDashboard.financeApp.dto.FinancialRecordResponseDTO;
import com.financeDashboard.financeApp.enums.RecordType;
import com.financeDashboard.financeApp.repository.FinancialRecordRepository;
import com.financeDashboard.financeApp.service.FinancialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/records")
@RequiredArgsConstructor
@Validated
public class FinancialRecordController {

    private final FinancialService financialService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FinancialRecordResponseDTO> createRecord(
            @Valid @RequestBody FinancialRecordRequestDTO request) {

        FinancialRecordResponseDTO response = financialService.createRecord(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    public ResponseEntity<FinancialRecordResponseDTO> getRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(financialService.getRecordById(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    public ResponseEntity<List<FinancialRecordResponseDTO>> getAllRecords(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) RecordType type,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {

        return ResponseEntity.ok(
                financialService.getAllRecords(category, type, startDate, endDate)
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FinancialRecordResponseDTO> updateRecord(
            @PathVariable Long id,
            @Valid @RequestBody FinancialRecordRequestDTO request) {

        return ResponseEntity.ok(financialService.updateRecord(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {

        financialService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}
