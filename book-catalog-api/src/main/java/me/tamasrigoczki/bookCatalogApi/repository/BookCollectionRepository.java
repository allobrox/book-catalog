package me.tamasrigoczki.bookCatalogApi.repository;

import me.tamasrigoczki.bookCatalogApi.model.entity.BookCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCollectionRepository
        extends JpaRepository<BookCollection, Long> {
}
