package com.aston.kupriyanov.bankAccountRestApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "owner_id")
    private Beneficiary owner;
    @Column(name = "number")
    private String number;
    @Column(name = "balance")
    private long balance;
    @CreatedDate
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sourceAccount", fetch = FetchType.EAGER)
    private List<Transaction> transactions;

    public Account() {
        this.number = generateNumber();
    }

    public void addTransaction(Transaction transaction) {
        if (transactions == null) {
            transactions = new ArrayList<>();
        }
        transactions.add(transaction);
        transaction.setSourceAccount(this);
    }

    private String generateNumber() {
        String number = "";
        Random value = new Random();

        int count = 0;
        int n = 0;
        for (int i = 0; i < 16; i++) {
            if (count == 4) {
                number += " ";
                count = 0;
            } else
                n = value.nextInt(10);
            number += Integer.toString(n);
            count++;
        }
        return number;
    }
}
