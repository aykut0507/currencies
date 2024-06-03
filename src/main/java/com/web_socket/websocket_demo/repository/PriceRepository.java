package com.web_socket.websocket_demo.repository;

import com.web_socket.websocket_demo.model.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface PriceRepository extends JpaRepository<Price, Long> {

}
