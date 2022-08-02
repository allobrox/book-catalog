package me.tamasrigoczki.bookCatalogApi.repository;

import me.tamasrigoczki.bookCatalogApi.model.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, String> {

    Optional<List<Book>> findAllByCollectionId(Long collectionId);
}
