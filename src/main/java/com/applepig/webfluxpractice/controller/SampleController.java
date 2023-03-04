package com.applepig.webfluxpractice.controller;

import java.time.Duration;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.applepig.webfluxpractice.data.Person;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class SampleController {

    @GetMapping("/hello")
    private Flux<String> hello(){
        return Flux.just("hello","workd","apple","pig");
    }
    @GetMapping("/person")
    private Person person() throws InterruptedException{   
        System.out.println("person befoe sleep");
        Thread.sleep(5000);
        System.out.println("person after sleep");
        return new Person(38,"applepig");
    }
    @GetMapping("/person/flux")
    private Flux<Person> personFlux() throws InterruptedException{   
        System.out.println("personFlux begin");
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1); // Java8의 무한 Stream
        System.out.println("personFlux end");
        return Flux.fromStream(stream.limit(20)).delayElements(Duration.ofSeconds(1))
                .map(i -> new Person(i,"hello, user"+String.valueOf(i) )  );
    }

}
