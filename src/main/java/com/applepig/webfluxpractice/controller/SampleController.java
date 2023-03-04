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
    @GetMapping("/get")
    private Mono<Person> get() throws InterruptedException{   
        System.out.println("get begin");
        Mono<Person> result = WebClient.create("http://localhost:8080").get().uri("/person").retrieve().bodyToMono(Person.class);
        System.out.println("get end");
        return result;
    }

    @GetMapping("/get2")
    private Person get2() throws InterruptedException{   
        System.out.println("get2 begin");
        Mono<Person> result = WebClient.create("http://localhost:8080").get().uri("/person").retrieve().bodyToMono(Person.class);
        System.out.println("get2 end");
        return Person.builder().age(20).name("default").build();
    }

    @GetMapping("/exp/flux")
    private Flux<Person> expFlux() throws InterruptedException{   
        System.out.println("expFlux begin");
        Flux<Person> result = 
            WebClient.create("http://localhost:8080")
                    .get()
                    .uri("/person/flux")
                    .retrieve()
                    .bodyToFlux(Person.class);
        System.out.println("expFlux end");
        return result;
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
