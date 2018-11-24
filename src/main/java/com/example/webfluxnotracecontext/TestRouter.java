package com.example.webfluxnotracecontext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
class TestRouter {

    @Bean
    public RouterFunction<ServerResponse> testRoutes(TestHandler handler) {
        return route(POST("/test").and(accept(APPLICATION_JSON)), handler::save);
    }
}
