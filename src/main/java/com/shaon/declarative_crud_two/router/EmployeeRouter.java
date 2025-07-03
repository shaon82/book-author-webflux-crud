package com.shaon.declarative_crud_two.router;


import com.shaon.declarative_crud_two.handler.EmployeeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@RestController
public class EmployeeRouter {

    @Bean
    public RouterFunction<?> routerFunction(EmployeeHandler handler){
        return RouterFunctions.route().path("/api/v1/employee",
                builder -> builder
                        .GET("/get-all",handler::getAllEmployee)
                        .POST("/save",handler::saveEmployee)
                        .PUT("/update/{id}",handler::updateEmployee)
                        .DELETE("/delete/{id}",handler::delete)
                        .GET("/find/{id}",handler::findById)
                )
                .build();

    }
}
