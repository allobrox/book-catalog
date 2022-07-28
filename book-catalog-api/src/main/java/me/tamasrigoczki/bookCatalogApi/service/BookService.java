package me.tamasrigoczki.bookCatalogApi.service;

import lombok.AllArgsConstructor;
import me.tamasrigoczki.bookCatalogApi.model.entity.Book;
import me.tamasrigoczki.bookCatalogApi.repository.BookRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    public void addBook(Book book) {
        if (bookRepository.existsById(book.getId())) {
            throw new IllegalArgumentException(book.getId());
        } else {
            bookRepository.save(book);
        }
    }

    public Book getById(String id){
        return bookRepository.findById(id).orElseThrow();
    }
}
