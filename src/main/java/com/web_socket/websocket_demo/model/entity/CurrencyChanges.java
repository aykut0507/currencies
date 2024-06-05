package com.web_socket.websocket_demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "currency_changes")
@Data
public class CurrencyChanges {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="currency_id")
    @JsonIgnore
    private Currency currency;

    @Column(name = "currency_value", precision = 12, scale = 2)
    private BigDecimal currencyValue;

    @Column(name = "currency_name")
    private String name;

    @Column(name = "inserted_date")
    private Instant insertedDate;

    @Column(name = "change_type")
    private byte changeType;

}