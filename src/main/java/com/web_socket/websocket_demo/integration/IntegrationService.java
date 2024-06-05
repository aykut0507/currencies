package com.web_socket.websocket_demo.integration;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.web_socket.websocket_demo.model.constants.CurrencyConstants;
import com.web_socket.websocket_demo.model.entity.Currency;
import com.web_socket.websocket_demo.service.CurrencyService;
import com.web_socket.websocket_demo.util.DateUtil;
import com.web_socket.websocket_demo.util.HttpUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.net.HttpURLConnection;
import java.time.Instant;

@Service
public class IntegrationService {
    String currenciesUrl = "https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@{0}/v1/currencies/eur.json";
    private static final String PATTERN_FORMAT = "yyyy-MM-dd";
    //String currenciesUrl = MessageFormat.format(,today);

    private final DateUtil dateUtil;
    private final HttpUtil httpUtil;
    private final CurrencyService currencyService;

    public IntegrationService(DateUtil dateUtil, HttpUtil httpUtil, CurrencyService currencyService) {
        this.dateUtil = dateUtil;
        this.httpUtil = httpUtil;
        this.currencyService = currencyService;
    }

    public void getCurrenciesWithJob() throws UnirestException {
        String todayDate = dateUtil.getDateWithStringFormat(Instant.now(), PATTERN_FORMAT);
        String msg = MessageFormat.format(currenciesUrl, todayDate);

        HttpResponse<JsonNode> jsonNodeHttpResponse = httpUtil.getMethod(msg);

        if (jsonNodeHttpResponse.getStatus() == HttpURLConnection.HTTP_OK) {
            JSONObject responseObject = jsonNodeHttpResponse.getBody().getObject();
            JSONObject euroData = (JSONObject) responseObject.get("eur");
            Object tryPrice = euroData.get("try");
            Currency currency = new Currency();
            currency.setCurrencyValue(new BigDecimal(tryPrice.toString()));
            currency.setName(CurrencyConstants.Euro);
            Currency currencyOptional = currencyService.getCurrencyWithName(CurrencyConstants.Euro);
            if (currencyOptional != null) {
                currency.setId(currencyOptional.getId());
            }
            currencyService.saveCurrencies(currency);
        }
    }
}
