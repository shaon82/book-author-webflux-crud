package com.shaon.declarative_crud_two.handler;


import com.shaon.declarative_crud_two.model.BookDTO;
import com.shaon.declarative_crud_two.model.Books;
import com.shaon.declarative_crud_two.repository.AuthorRepository;
import com.shaon.declarative_crud_two.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class BookHandler {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookHandler(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    public Mono<ServerResponse> findBooks(ServerRequest request){
        return bookRepository.findAll()
                .flatMap(books -> authorRepository.findById(books.getAuthorId())
                        .map(author -> {
                            BookDTO bookDTO = new BookDTO(books.getBookName(),books.getPublishDate(),author);
                            return bookDTO;
                        })).collectList()
                .flatMap(bookDTOS -> ServerResponse.ok().bodyValue(bookDTOS));
    }


    public Mono<ServerResponse> saveBook(ServerRequest request){
        return request.bodyToMono(Books.class)
                .flatMap(books -> authorRepository.findById(books.getAuthorId())
                        .flatMap(author -> bookRepository.save(books)))
                .flatMap(saveBook-> ServerResponse.ok().bodyValue(saveBook))
                .switchIfEmpty(ServerResponse.badRequest().bodyValue("Invalid author ID"));
    }



}
