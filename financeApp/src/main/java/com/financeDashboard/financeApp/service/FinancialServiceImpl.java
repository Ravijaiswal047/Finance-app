package com.financeDashboard.financeApp.service;

import com.financeDashboard.financeApp.dto.DashboardSummaryDTO;
import com.financeDashboard.financeApp.dto.FinancialRecordRequestDTO;
import com.financeDashboard.financeApp.dto.FinancialRecordResponseDTO;
import com.financeDashboard.financeApp.entity.FinancialRecord;
import com.financeDashboard.financeApp.entity.User;
import com.financeDashboard.financeApp.enums.RecordType;
import com.financeDashboard.financeApp.repository.FinancialRecordRepository;
import com.financeDashboard.financeApp.repository.UserRepository;
import com.financeDashboard.financeApp.util.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FinancialServiceImpl implements FinancialService {

    private final FinancialRecordRepository recordRepository;
    private final UserRepository userRepository;

    @Override
    public FinancialRecordResponseDTO createRecord(FinancialRecordRequestDTO request) {

        log.info("Creating financial record: {}", request);

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        FinancialRecord record = FinancialRecord.builder()
                .amount(request.getAmount())
                .type(request.getType())
                .category(request.getCategory())
                .date(request.getDate())
                .note(request.getNote())
                .user(user)
                .build();

        record = recordRepository.save(record);

        return mapToResponse(record);
    }

    @Override
    @Transactional(readOnly = true)
    public FinancialRecordResponseDTO getRecordById(Long id) {

        FinancialRecord record = recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));

        return mapToResponse(record);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FinancialRecordResponseDTO> getAllRecords(
            String category,
            RecordType type,
            LocalDate startDate,
            LocalDate endDate) {

        List<FinancialRecord> records = recordRepository.findAll();

        return records.stream()
                .filter(r -> category == null || r.getCategory().equalsIgnoreCase(category))
                .filter(r -> type == null || r.getType() == type)
                .filter(r -> startDate == null || !r.getDate().isBefore(startDate))
                .filter(r -> endDate == null || !r.getDate().isAfter(endDate))
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public FinancialRecordResponseDTO updateRecord(Long id, FinancialRecordRequestDTO request) {

        FinancialRecord record = recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));

        record.setAmount(request.getAmount());
        record.setType(request.getType());
        record.setCategory(request.getCategory());
        record.setDate(request.getDate());
        record.setNote(request.getNote());

        return mapToResponse(recordRepository.save(record));
    }

    @Override
    public void deleteRecord(Long id) {

        if (!recordRepository.existsById(id)) {
            throw new ResourceNotFoundException("Record not found");
        }

        recordRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public DashboardSummaryDTO getDashboardSummary() {

        Double income = recordRepository.sumByType(RecordType.INCOME);
        Double expense = recordRepository.sumByType(RecordType.EXPENSE);

        double totalIncome = income != null ? income : 0.0;
        double totalExpense = expense != null ? expense : 0.0;

        return DashboardSummaryDTO.builder()
                .totalIncome(totalIncome)
                .totalExpense(totalExpense)
                .netBalance(totalIncome - totalExpense)
                .build();
    }

    //  Mapper Method
    private FinancialRecordResponseDTO mapToResponse(FinancialRecord record) {

        return FinancialRecordResponseDTO.builder()
                .id(record.getId())
                .amount(record.getAmount())
                .type(record.getType())
                .category(record.getCategory())
                .date(record.getDate())
                .note(record.getNote())
                .userId(record.getUser().getId())
                .build();
    }
}
