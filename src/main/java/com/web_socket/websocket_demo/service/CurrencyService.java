package com.web_socket.websocket_demo.service;

import com.web_socket.websocket_demo.model.constants.EntityChangeTypeConstants;
import com.web_socket.websocket_demo.model.entity.Currency;
import com.web_socket.websocket_demo.model.entity.CurrencyChanges;
import com.web_socket.websocket_demo.repository.CurrencyChangesRepository;
import com.web_socket.websocket_demo.repository.CurrencyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class CurrencyService {
    private final SocketService socketService;
    private final CurrencyRepository currencyRepository;
    private final CurrencyChangesRepository currencyChangesRepository;

    public CurrencyService(SocketService socketService, CurrencyRepository currencyRepository, CurrencyChangesRepository currencyChangesRepository) {
        this.socketService = socketService;
        this.currencyRepository = currencyRepository;
        this.currencyChangesRepository = currencyChangesRepository;
    }

    public void saveCurrencies(Currency currency) {
        byte changeType = getChangeType(currency);

        currencyRepository.save(currency);

        saveCurrencyChanges(currency, changeType);
    }

    @Transactional
    public Currency getCurrencyWithName(String currency) {
        List<Currency> currencies = currencyRepository.findAllByName(currency);
        if (currencies.size() > 0) {
            return currencies.get(0);
        }
        return null;
    }

    byte getChangeType(Currency currency) {
        if (currency.getId() == null) {
            return EntityChangeTypeConstants.NEW;
        }
        return EntityChangeTypeConstants.UPDATE;
    }

    void saveCurrencyChanges(Currency currency, byte changeType) {
        CurrencyChanges currencyChange = new CurrencyChanges();

        currencyChange.setCurrency(currency);
        currencyChange.setCurrencyValue(currency.getCurrencyValue());
        currencyChange.setName(currency.getName());
        currencyChange.setInsertedDate(Instant.now());
        currencyChange.setChangeType(changeType);

        currencyChangesRepository.save(currencyChange);
    }
}
