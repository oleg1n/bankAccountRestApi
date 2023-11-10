package com.aston.kupriyanov.bankAccountRestApi.entity;

import com.aston.kupriyanov.bankAccountRestApi.constant.Operation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction_history")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "operation")
    private String operation;
    @Column(name = "source_account_id")
    private UUID sourceAccount;
    @Column(name = "destination_account_id")
    private UUID destinationAccount;
    @Column(name = "value")
    private long value;
    @CreatedDate
    @Column(name = "date")
    private LocalDateTime date;
}
