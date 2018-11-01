package com.nick.jdk8.time;

import java.time.Clock;

public class ClockUsage {

    private static void checkClock() {
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.instant());
    }

    public static void main(String[] args) {
        checkClock();
    }

}
