package com.aston.kupriyanov.bankAccountRestApi.entity;

import com.aston.kupriyanov.bankAccountRestApi.util.AccountHelper;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "account")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Beneficiary owner;
    @Column(name = "number")
    private String number;
    @Column(name = "balance")
    private long balance;
    @CreatedDate
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sourceAccount")
    private List<Transaction> transactions;

    public Account() {
        this.number = AccountHelper.generateNumber();
    }

    public void addTransaction(Transaction transaction) {
        if (transactions == null) {
            transactions = new ArrayList<>();
        }
        transactions.add(transaction);
        transaction.setSourceAccount(this);
    }


}
