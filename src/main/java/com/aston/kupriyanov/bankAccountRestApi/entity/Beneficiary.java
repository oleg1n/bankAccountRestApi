package com.aston.kupriyanov.bankAccountRestApi.entity;

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
@Table(name = "beneficiary")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Beneficiary {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "pin_code")
    private String pinCode;
    @Column(name = "create_date")
    @CreatedDate
    private LocalDateTime createDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Account> accounts;

    public Beneficiary(String name, String pinCode){
        this.name = name;
        this.pinCode = pinCode;
    }

    public void addAccount(Account account){
        if (accounts == null){
            accounts = new ArrayList<>();
        }
        accounts.add(account);
        account.setOwner(this);
    }
}
