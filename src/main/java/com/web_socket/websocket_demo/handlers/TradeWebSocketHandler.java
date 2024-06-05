package com.web_socket.websocket_demo.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class TradeWebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    public TradeWebSocketHandler() {
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println(session);
        /*List<Price> prices = new ArrayList<>(); //priceRepository.findAll();
        System.out.println(prices);
        if (prices.size() > 0) {
            TextMessage message = new TextMessage(objectMapper.writeValueAsString(prices.get(0)));
            session.sendMessage(message);
            Thread.sleep(4000);
        }*/

        float oldPrice = 0.0f;

        //Publishing new stock prices every one second for 100 times
        /*for (int i=0; i < 100; i ++){
            //Calculating Random stock price between 12$ to 13$
            float stockPrice = 12 + r.nextFloat() * (13 - 12);
            float roundedPrice = (float) (Math.round(stockPrice * 100.0) / 100.0);

            //Creating a Stock Object
            Stock stock = new Stock("Amazon",
                    "https://cdn.cdnlogo.com/logos/a/77/amazon-dark.svg",
                    roundedPrice);
            //Finding whether the stock pric increased or decreased
            if (roundedPrice > oldPrice){
                stock.setIncreased(true);
            }
            oldPrice = roundedPrice;

            //Sending StockPrice
            TextMessage message = new TextMessage(objectMapper.writeValueAsString(stock));
            session.sendMessage(message);
            Thread.sleep(1000);
        }*/
        sessions.add(session);
    }
}