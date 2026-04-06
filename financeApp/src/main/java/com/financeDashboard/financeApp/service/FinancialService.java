package com.financeDashboard.financeApp.service;

import com.financeDashboard.financeApp.dto.DashboardSummaryDTO;
import com.financeDashboard.financeApp.dto.FinancialRecordRequestDTO;
import com.financeDashboard.financeApp.dto.FinancialRecordResponseDTO;
import com.financeDashboard.financeApp.enums.RecordType;

import java.time.LocalDate;
import java.util.List;

public interface FinancialService {

    FinancialRecordResponseDTO createRecord(FinancialRecordRequestDTO request);

    FinancialRecordResponseDTO getRecordById(Long id);

    List<FinancialRecordResponseDTO> getAllRecords(
            String category,
            RecordType type,
            LocalDate startDate,
            LocalDate endDate
    );

    FinancialRecordResponseDTO updateRecord(Long id, FinancialRecordRequestDTO request);

    void deleteRecord(Long id);

    DashboardSummaryDTO getDashboardSummary();
}
