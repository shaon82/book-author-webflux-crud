package com.shaon.declarative_crud_two.router;


import com.shaon.declarative_crud_two.handler.BookHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@RestController
public class BookRouter {


    @Bean
    public RouterFunction<ServerResponse> routeBook(BookHandler bookHandler){
        return RouterFunctions.route().path("/api/v1/book",
                builder -> builder
                        .GET("/find-all-book",bookHandler::findBooks)
                        .POST("/save",bookHandler::saveBook)
        ).build();
    }
}
