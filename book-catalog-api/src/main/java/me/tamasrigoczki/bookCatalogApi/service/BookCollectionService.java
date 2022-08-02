package me.tamasrigoczki.bookCatalogApi.service;

import lombok.AllArgsConstructor;
import me.tamasrigoczki.bookCatalogApi.model.entity.BookCollection;
import me.tamasrigoczki.bookCatalogApi.repository.BookCollectionRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookCollectionService {

    private final BookCollectionRepository bookCollectionRepository;

    public BookCollection createBookCollection(BookCollection bookCollection) {
        return bookCollectionRepository.save(bookCollection);
    }
}
