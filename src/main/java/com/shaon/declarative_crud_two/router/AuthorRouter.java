package com.shaon.declarative_crud_two.router;


import com.shaon.declarative_crud_two.handler.AuthorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@RestController
public class AuthorRouter {

    @Bean
    public RouterFunction<ServerResponse> authorRoute(AuthorHandler authorHandler){
        return RouterFunctions.route().path("/api/v1/author",
                builder -> builder
                        .GET("/find-all-author",authorHandler::getAuthors)
                        .POST("/save",authorHandler::saveAuthor)
                        .PUT("/update/{id}",authorHandler::updateAuthor)
                        .GET("/find-by-id/{id}",authorHandler::findById)
                        .DELETE("/delete/{id}",authorHandler::deleteAuthor)
        ).build();
    }
}
