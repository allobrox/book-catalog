package me.tamasrigoczki.bookCatalogApi.repository;

import me.tamasrigoczki.bookCatalogApi.model.entity.BookEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookEntryRepository extends JpaRepository<BookEntry, Long> {
}
