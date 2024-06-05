package com.web_socket.websocket_demo.util;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class DateUtil {

    public String getDateWithStringFormat(Instant instant, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format)
                .withZone(ZoneId.systemDefault());
        return formatter.format(Instant.parse(instant.toString()));
    }
}
