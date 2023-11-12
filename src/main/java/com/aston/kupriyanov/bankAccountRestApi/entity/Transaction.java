package com.aston.kupriyanov.bankAccountRestApi.entity;

import jakarta.persistence.*;
import lombok.Data;
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
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "source_account_id")
    private Account sourceAccount;
    //@Column(name = "destination_account_id")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "destination_account_id")
    private Account destinationAccount;
    @Column(name = "value")
    private long value;
    @CreatedDate
    @Column(name = "date")
    private LocalDateTime date;

    public Transaction(){}

    public Transaction(String operation, Account destinationAccount, long value){
        this.operation = operation;
        this.destinationAccount = destinationAccount;
        this.value = value;
    }
}
