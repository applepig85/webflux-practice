package com.applepig.webfluxpractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class SampleController {

    @GetMapping("/")
    private Flux<String> hello(){
        return Flux.just("hello","workd","apple","pig");
    }

}
