package com.example.webfluxnotracecontext;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
class TestHandler {

    private final TestRepository repository;

    TestHandler(TestRepository repository) {
        this.repository = repository;
    }

    /**
     * @should set the context
     */
    Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(TestEntity.class)
                .map(TestEntity::setContext)
                .flatMap(repository::save)
                .flatMap(entity -> ok().build());
    }
}
