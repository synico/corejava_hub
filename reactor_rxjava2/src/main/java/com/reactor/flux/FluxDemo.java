package com.reactor.flux;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
		FluxDemo fluxDemo = new FluxDemo();
		fluxDemo.time();
	}

}
