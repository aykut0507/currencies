package com.web_socket.websocket_demo.repository;

import com.web_socket.websocket_demo.model.entity.CurrencyChanges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CurrencyChangesRepository extends JpaRepository<CurrencyChanges, Long> {
}
