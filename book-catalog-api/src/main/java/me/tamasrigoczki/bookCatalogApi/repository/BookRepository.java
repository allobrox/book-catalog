package me.tamasrigoczki.bookCatalogApi.repository;

import me.tamasrigoczki.bookCatalogApi.model.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, String> {
}
