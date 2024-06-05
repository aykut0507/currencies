package com.web_socket.websocket_demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "currency")
@Data
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "currency_name")
    private String name;

    @Column(name = "currency_value", precision = 12, scale = 2)
    private BigDecimal currencyValue;

    @OneToMany(mappedBy = "currency")
    @JsonIgnore
    private List<CurrencyChanges> currencyChanges;
}