package com.web_socket.websocket_demo.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class HttpUtil {

    public HttpResponse<JsonNode> getMethod(String requestUrl) throws UnirestException {
        return Unirest.get(requestUrl).asJson();
    }
}
