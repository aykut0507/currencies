package com.web_socket.websocket_demo.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web_socket.websocket_demo.model.entity.Price;
import com.web_socket.websocket_demo.repository.PriceRepository;
import com.web_socket.websocket_demo.service.SocketService;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Configuration
@EnableScheduling
public class SocketResource {

    private final PriceRepository priceRepository;
    private final SocketService socketService;
    private SimpMessagingTemplate template;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public SocketResource(PriceRepository priceRepository, SocketService socketService) {
        this.priceRepository = priceRepository;
        this.socketService = socketService;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public List<Price> currencies() throws Exception {
        System.out.println("asd");
        List<Price> prices = null;
        //for (int i = 0; i < 10; i++) {
            //Thread.sleep(5000); // simulated delay
            prices = priceRepository.findAll();
            String writeValueAsString = objectMapper.writeValueAsString(prices);
            socketService.sendMessage("greetings", writeValueAsString);
        //}
        return prices;
    }

    /*@MessageMapping("/hello")
    @Scheduled(fixedDelay = 5000L)
    @SendTo("/topic/greetings")
    public void sendPong() throws JsonProcessingException {
        List<Price> prices = priceRepository.findAll();
        String writeValueAsString = objectMapper.writeValueAsString(prices);

        template.convertAndSend("/topic/greetings", writeValueAsString);
    }*/

    @Scheduled(fixedDelay=10000)
    public void scheduleFixedDelayTask() throws Exception {
        this.currencies();
    }
}
