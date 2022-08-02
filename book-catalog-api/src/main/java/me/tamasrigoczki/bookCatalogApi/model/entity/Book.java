package me.tamasrigoczki.bookCatalogApi.model.entity;

import lombok.Data;
import me.tamasrigoczki.bookCatalogApi.model.enums.BookStatus;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Data
public class Book {

    @Id
    private String id;

    private String author;

    private Long collectionId;
    private int pages;

    @Enumerated(EnumType.STRING)
    private BookStatus status;
    private String title;
}
