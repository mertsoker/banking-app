package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Depositors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Depositor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "deposit_date")
    private LocalDate depositDate;

    @Column(precision = 10, scale = 2)
    private BigDecimal amount;
}