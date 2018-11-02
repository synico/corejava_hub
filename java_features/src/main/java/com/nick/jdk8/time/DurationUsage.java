package com.nick.jdk8.time;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;

public class DurationUsage {

    public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");

    public static void getBetween() {
        LocalDateTime beginTime = LocalDateTime.of(2018, 10, 12, 00, 20, 10);
        LocalDateTime endTime = LocalDateTime.parse("20181012 00:50:00", dtf);
        Duration chargingTime = Duration.between(beginTime, endTime);
        System.out.println(chargingTime);
    }

    public static void main(String[] args) {
        getBetween();
    }

}
