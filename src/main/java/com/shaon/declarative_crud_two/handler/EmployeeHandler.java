package com.shaon.declarative_crud_two.handler;

import com.shaon.declarative_crud_two.model.Employee;
import com.shaon.declarative_crud_two.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class EmployeeHandler {

    private final EmployeeRepository employeeRepository;

    public EmployeeHandler(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public Mono<ServerResponse> getAllEmployee(ServerRequest request){
        return ServerResponse.ok().body(employeeRepository.findAll(), Employee.class);
    }

    public Mono<ServerResponse> saveEmployee(ServerRequest request) {
        Mono<Employee> employeeMono = request.bodyToMono(Employee.class);
        return request.bodyToMono(Employee.class)
                .flatMap(employeeRepository::save)
                .flatMap(saveEmployee-> ServerResponse.status(HttpStatus.CREATED).bodyValue(saveEmployee));
    }



    public Mono<ServerResponse> updateEmployee(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        Mono<Employee> updatedEmployee = request.bodyToMono(Employee.class);
        return employeeRepository.findById(id).flatMap(existingEmployee -> updatedEmployee.flatMap(uEmployee->{
            existingEmployee.setName(uEmployee.getName());
            existingEmployee.setEmail(uEmployee.getEmail());
            return employeeRepository.save(existingEmployee);
        })).flatMap(employee -> ServerResponse.ok().bodyValue(employee)).switchIfEmpty(ServerResponse.notFound().build());
    }


    public Mono<ServerResponse> delete(ServerRequest request){
        return employeeRepository.findById(Long.valueOf(request.pathVariable("id")))
                .flatMap(e-> employeeRepository.delete(e).then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        return employeeRepository.findById(Long.valueOf(request.pathVariable("id"))).flatMap(employee -> ServerResponse.ok().bodyValue(employee))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
