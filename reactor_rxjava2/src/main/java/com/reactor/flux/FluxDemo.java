package com.reactor.flux;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import reactor.core.publisher.Flux;

public class FluxDemo {
	
	private void genFluxSeq() {
		Flux.generate(sink -> {
			sink.next("Hello");
			sink.complete();
		}).subscribe(System.out::println);
	}
	
	private void time() {
		ZoneId east8 = ZoneId.of("Asia/Shanghai");
		ZonedDateTime zdt = ZonedDateTime.now(east8);
		StringBuilder prefix = new StringBuilder();
		prefix.append(zdt.getYear());
		prefix.append("-");
		if(zdt.getMonthValue() < 10) {
			prefix.append("0");
		}
		prefix.append(zdt.getMonthValue());
		
		prefix.append("-");
		prefix.append(zdt.getDayOfMonth());

		String startTime = prefix.toString() + " 08:00:00";
		String endTime = prefix.toString() + " 20:00:00";
		ZonedDateTime startZoneTime = ZonedDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(east8));
		ZonedDateTime endZoneTime = ZonedDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(east8));
		if(zdt.isBefore(startZoneTime)||zdt.isAfter(endZoneTime)) {
		} else {
			System.out.println("Business hour" + zdt.toString());
		}
	}

	public static void main(String[] args) {
//		FluxDemo fluxDemo = new FluxDemo();
//		fluxDemo.time();

		List<Date> dateList = new ArrayList<>();
		dateList.add(new Date(System.currentTimeMillis() - 100000));
        dateList.add(new Date(System.currentTimeMillis() - 50000));
        dateList.add(null);
        dateList.add(new Date(System.currentTimeMillis() - 10000));
        dateList.add(new Date(System.currentTimeMillis()));

        dateList = dateList.stream()
            .sorted((Date d1, Date d2) -> {
                if(d1 == null) {
                    System.out.println("xxx");
                    return 1;
                } else if(d2 == null) {
                    return -1;
                } else {
                    if(d1.before(d2)) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            })
            .collect(Collectors.toList());

        dateList.forEach(System.out::println);

	}

}
