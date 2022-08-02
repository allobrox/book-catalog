package me.tamasrigoczki.bookCatalogApi.service;

import lombok.AllArgsConstructor;
import me.tamasrigoczki.bookCatalogApi.model.entity.Book;
import me.tamasrigoczki.bookCatalogApi.model.enums.BookStatus;
import me.tamasrigoczki.bookCatalogApi.repository.BookRepository;
import org.apache.commons.lang3.StringUtils;
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
            book.setStatus(BookStatus.TO_READ);
            bookRepository.save(book);
        }
    }

    public void modifyBook(Book book) {
        final String bookId = book.getId();
        checkBook(!bookRepository.existsById(bookId), bookId);
        bookRepository.save(book);
        ;
    }

    public Book getById(String id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public void deleteBook(String bookId) {
        checkBook(StringUtils.isEmpty(bookId) ||
                !bookRepository.existsById(bookId), bookId);
        bookRepository.deleteById(bookId);
    }

    private void checkBook(boolean condition, String bookId) {
        if (condition) {
            throw new NoSuchElementException(String.format("Book not exists! " +
                    "ID: %s", bookId));
        }
    }
}
