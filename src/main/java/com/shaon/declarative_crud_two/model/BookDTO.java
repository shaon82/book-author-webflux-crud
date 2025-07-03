package com.shaon.declarative_crud_two.model;

import java.time.LocalDate;

public class BookDTO {

    private String bookName;

    private LocalDate publishDate;

    private Author author;

    public BookDTO() {
    }

    public BookDTO(String bookName, LocalDate publishDate, Author author) {
        this.bookName = bookName;
        this.publishDate = publishDate;
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
