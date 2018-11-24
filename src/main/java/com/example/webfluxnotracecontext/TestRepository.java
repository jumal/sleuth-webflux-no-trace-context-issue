package com.example.webfluxnotracecontext;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static reactor.core.publisher.Mono.just;

@Component
class TestRepository {

    <S extends TestEntity> Mono<S> save(S entity) {
        return just(entity);
    }
}
