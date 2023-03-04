package com.applepig.webfluxpractice.controller;

import java.time.Duration;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.applepig.webfluxpractice.data.Person;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exp")
public class ExperimentController {

    /*
     * expected:
     * actual:
     */
    @GetMapping("/mono/1")
    private Mono<Person> get() throws InterruptedException{   
        System.out.println("get begin");
        Mono<Person> result = WebClient.create("http://localhost:8080").get().uri("/person").retrieve().bodyToMono(Person.class);
        System.out.println("get end");
        return result;
    }

    /*
     * expected:
     * actual:
     */
    @GetMapping("/mono/2")
    private Person get2() throws InterruptedException{   
        System.out.println("get2 begin");
        Mono<Person> result = WebClient.create("http://localhost:8080").get().uri("/person").retrieve().bodyToMono(Person.class);
        System.out.println("get2 end");
        return Person.builder().age(20).name("default").build();
    }

    /*
     * expected:
     * actual:
     */
    @GetMapping("/flux/1")
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
}
