package com.applepig.webfluxpractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.applepig.webfluxpractice.data.Person;

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

}
