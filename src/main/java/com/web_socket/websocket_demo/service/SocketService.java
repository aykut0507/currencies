package com.web_socket.websocket_demo.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SocketService {
    private final SimpMessagingTemplate messagingTemplate;

    public SocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessage(final String topicSuffix, String payload) {
        messagingTemplate.convertAndSend("/topic/" + topicSuffix, payload);
    }
}
