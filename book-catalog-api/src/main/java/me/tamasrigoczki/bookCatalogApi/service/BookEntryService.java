package me.tamasrigoczki.bookCatalogApi.service;

import lombok.AllArgsConstructor;
import me.tamasrigoczki.bookCatalogApi.model.entity.Book;
import me.tamasrigoczki.bookCatalogApi.model.entity.BookEntry;
import me.tamasrigoczki.bookCatalogApi.repository.BookEntryRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@AllArgsConstructor
public class BookEntryService {

    private final BookEntryRepository bookEntryRepository;

    public BookEntry saveBookEntry(BookEntry entity) {
        return bookEntryRepository.save(entity);
    }

    public void modifyBookEntry(BookEntry entry) {
        final Long entryId = entry.getId();
        if (entryId == null) {
            throw new NoSuchElementException("Can't modify entry without ID!");
        }
        if (!bookEntryRepository.existsById(entryId)) {
            throw new NoSuchElementException(String.format("Entry not " +
                    "exists! ID: %d", entryId));
        }

        bookEntryRepository.save(entry);
    }

    public List<BookEntry> getByBookId(String bookId) {
        Book book = new Book();
        book.setId(bookId);
        List<BookEntry> bookEntriesByBook =
                bookEntryRepository.getBookEntriesByBook(book);
        if (CollectionUtils.isEmpty(bookEntriesByBook)) {
            throw new NoSuchElementException(String.format("No entries for " +
                    "book with ID: %s", bookId));
        }
        return bookEntriesByBook;
    }

    public BookEntry getById(Long entryId) {
        return bookEntryRepository.findById(entryId).orElseThrow();
    }
}
