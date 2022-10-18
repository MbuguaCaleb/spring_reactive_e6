package com.example.spring_reactive_e6.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DemoController {

    //When working with rest endpoints,springboot takes care of our subscribers
    @GetMapping(value = "/demo", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> demo() {

        Flux<String> f1 = Flux.just("AAA", "AAA", "BBB", "BBB", "CCC", "DDD", "EEE", "FF", "AAA");
        Flux<String> f2 = Flux.just("mercy", "ruth", "sharon","brian");

        return f1
//                .filter(s->s.length() % 2 == 0)
//                .log();
                //       .distinct()
                //.distinctUntilChanged();
                // .map(s->s.length());
                // .flatMap(s->Flux.just(s.split("")))
                //.doOnNext();
               // .collect(Collectors.toList());
                // .collect(Collectors.summingInt(s->s.length()));
                //.concatWith(f2);
              //  .doOnNext(s-> System.out.println(s))
                //.thenMany(f2);
                .zipWith(f2,(x,y) -> x + "=" +y )
                .log();


    }
}
