package me.tamasrigoczki.bookCatalogApi.repository;

import me.tamasrigoczki.bookCatalogApi.model.entity.Book;
import me.tamasrigoczki.bookCatalogApi.model.entity.BookEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookEntryRepository extends JpaRepository<BookEntry, Long> {
    List<BookEntry> getBookEntriesByBook(Book book);
}
