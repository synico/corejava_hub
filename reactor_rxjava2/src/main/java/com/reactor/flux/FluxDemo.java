package com.reactor.flux;

import reactor.core.publisher.Flux;

public class FluxDemo {
	
	private void genFluxSeq() {
		Flux.generate(sink -> {
			sink.next("Hello");
			sink.complete();
		}).subscribe(System.out::println);
	}

	public static void main(String[] args) {
		FluxDemo fluxDemo = new FluxDemo();
		fluxDemo.genFluxSeq();
	}

}
