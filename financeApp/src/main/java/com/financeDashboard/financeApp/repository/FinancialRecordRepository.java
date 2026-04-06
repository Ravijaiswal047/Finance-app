package com.financeDashboard.financeApp.repository;

import com.financeDashboard.financeApp.entity.FinancialRecord;
import com.financeDashboard.financeApp.enums.RecordType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {
    List<FinancialRecord> findByCategoryIgnoreCase(String category);

    List<FinancialRecord> findByType(RecordType type);

    List<FinancialRecord> findByDateBetween(LocalDate start, LocalDate end);

    List<FinancialRecord> findByCategoryIgnoreCaseAndTypeAndDateBetween(
            String category,
            RecordType type,
            LocalDate startDate,
            LocalDate endDate
    );

    // Dashboard: total by type
    @Query("SELECT SUM(f.amount) FROM FinancialRecord f WHERE f.type = :type")
    Double sumByType(RecordType type);

    // Dashboard: category-wise totals
    @Query("SELECT f.category, SUM(f.amount) FROM FinancialRecord f GROUP BY f.category")
    List<Object[]> getCategoryWiseTotals();

    // Recent records (latest first)
    List<FinancialRecord> findTop10ByOrderByDateDesc();

}
