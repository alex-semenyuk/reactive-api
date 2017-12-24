package com.baeldung.reactiveAPI.api;

import com.baeldung.reactiveAPI.Foo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
public class ReactiveApiApplication {

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE, value = "/foo")
    public Flux<Foo> getFoo() {
        Flux<Foo> foo = Flux.fromStream(Stream.generate(() -> new Foo(System.currentTimeMillis(), "Foo Name")));
        Flux<Long> interval = Flux.interval(Duration.ofMillis(1000));
        return Flux.zip(foo, interval)
                .map(Tuple2::getT1);
    }

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApiApplication.class, args);
	}
}
