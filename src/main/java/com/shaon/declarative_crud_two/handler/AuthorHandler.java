package com.shaon.declarative_crud_two.handler;


import com.shaon.declarative_crud_two.model.Author;
import com.shaon.declarative_crud_two.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class AuthorHandler {

    private final AuthorRepository authorRepository;

    public AuthorHandler(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public Mono<ServerResponse> getAuthors(ServerRequest request){
        return ServerResponse.ok().body(authorRepository.findAll(), Author.class);
    }


    // this is alternative way for find all method
    public Mono<ServerResponse> findAuthors(ServerRequest request){
        return authorRepository.findAll().collectList()
                .flatMap(authors -> ServerResponse.ok().bodyValue(authors));
    }


    public Mono<ServerResponse> saveAuthor(ServerRequest request){
        return request.bodyToMono(Author.class)
                .flatMap(authorRepository::save)
                .flatMap(savedAuthor->ServerResponse.ok().bodyValue(savedAuthor));
    }


    public Mono<ServerResponse> findById(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        return authorRepository.findById(id).flatMap(author->ServerResponse.ok().bodyValue(author)).switchIfEmpty(ServerResponse.notFound().build());
    }


    public Mono<ServerResponse> updateAuthor(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        Mono<Author> updatedAuthor = request.bodyToMono(Author.class);
        return authorRepository.findById(id)
                .flatMap(existingAuthor-> updatedAuthor.flatMap(updateAuthor->{
                    existingAuthor.setAuthorName(updateAuthor.getAuthorName());
                    return authorRepository.save(existingAuthor);
                })).flatMap(author -> ServerResponse.ok().bodyValue(author))
                .switchIfEmpty(ServerResponse.notFound().build());
    }


    public Mono<ServerResponse> deleteAuthor(ServerRequest request){
        return authorRepository.findById(Long.valueOf(request.pathVariable("id")))
                .flatMap(author -> authorRepository.delete(author).then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
