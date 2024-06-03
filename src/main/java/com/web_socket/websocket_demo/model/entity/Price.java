package com.web_socket.websocket_demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "price")
@Data
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "price_name")
    private String name;

    @Column(name = "price_value", precision = 12, scale = 2)
    private BigDecimal priceValue;

}