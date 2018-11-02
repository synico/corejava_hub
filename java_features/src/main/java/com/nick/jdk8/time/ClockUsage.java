package com.nick.jdk8.time;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class ClockUsage {

    private static void checkClock() {
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.instant());
    }

    private static void getSystemClock() {
        Clock sysClock = Clock.systemUTC();
        System.out.println("getSystemClock: " + sysClock.instant() + ", zoneId: " + sysClock.getZone().toString());
    }

    private static void getCTTClock() {
        ZoneId zoneId = ZoneId.of(ZoneId.SHORT_IDS.get("CTT"));
        Clock cttClock = Clock.system(zoneId);
        Instant instant = cttClock.instant();
        System.out.println("getISTClock: Zone: " + cttClock.getZone() + ", time: " + instant.atZone(zoneId));
    }

    public static void main(String[] args) {
        checkClock();
        getSystemClock();
        getCTTClock();
    }

}
