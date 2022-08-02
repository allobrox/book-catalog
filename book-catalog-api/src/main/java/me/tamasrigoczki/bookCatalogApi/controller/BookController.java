package me.tamasrigoczki.bookCatalogApi.controller;

import lombok.RequiredArgsConstructor;
import me.tamasrigoczki.bookCatalogApi.model.dto.BookDto;
import me.tamasrigoczki.bookCatalogApi.model.dto.CreateBookDto;
import me.tamasrigoczki.bookCatalogApi.model.entity.Book;
import me.tamasrigoczki.bookCatalogApi.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@Valid @RequestBody CreateBookDto bookDto) {
        bookService.addBook(modelMapper.map(bookDto, Book.class));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void modifyBook(@Valid @RequestBody BookDto bookDto) {
        bookService.modifyBook(modelMapper.map(bookDto, Book.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookDto> getBookById(@PathVariable String id) {
        return ResponseEntity.ok(modelMapper.map(bookService.getById(id),
                BookDto.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
    }
}
