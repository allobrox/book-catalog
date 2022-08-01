package me.tamasrigoczki.bookCatalogApi.service;

import lombok.AllArgsConstructor;
import me.tamasrigoczki.bookCatalogApi.model.entity.BookEntry;
import me.tamasrigoczki.bookCatalogApi.repository.BookEntryRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookEntryService {

    private final BookEntryRepository bookEntryRepository;

    public BookEntry saveBookEntry(BookEntry entity) {
        return bookEntryRepository.save(entity);
    }
}
