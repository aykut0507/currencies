package com.web_socket.websocket_demo.service;

import com.web_socket.websocket_demo.model.dto.CurrencyDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
    private final SocketService socketService;

    public CurrencyService(SocketService socketService) {
        this.socketService = socketService;
    }

    public void sendCurrenciesWithSocket() {
        //socketService.sendMessage("Dolar");
    }
}
