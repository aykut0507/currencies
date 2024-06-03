package com.web_socket.websocket_demo.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CurrencyDto {
    private String name;

    private BigDecimal priceValue;

}
