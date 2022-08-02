package me.tamasrigoczki.bookCatalogApi.service;

import lombok.AllArgsConstructor;
import me.tamasrigoczki.bookCatalogApi.model.entity.Book;
import me.tamasrigoczki.bookCatalogApi.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

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

    public void modifyBook(Book book) {
        final String bookId = book.getId();
        if (!bookRepository.existsById(bookId)) {
            throw new NoSuchElementException(String.format("Book not exists! " +
                    "ID: %s", bookId));
        }
        bookRepository.save(book);
        ;
    }

    public Book getById(String id) {
        return bookRepository.findById(id).orElseThrow();
    }
}
