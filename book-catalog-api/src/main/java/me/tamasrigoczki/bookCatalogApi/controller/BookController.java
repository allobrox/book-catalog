package me.tamasrigoczki.bookCatalogApi.controller;

import lombok.RequiredArgsConstructor;
import me.tamasrigoczki.bookCatalogApi.model.dto.BookDto;
import me.tamasrigoczki.bookCatalogApi.model.entity.Book;
import me.tamasrigoczki.bookCatalogApi.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@Valid @RequestBody BookDto bookDto) {
        bookService.addBook(new ModelMapper().map(bookDto, Book.class));
    }
}
