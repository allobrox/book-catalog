package me.tamasrigoczki.bookCatalogApi.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Book {

    @Id
    private String id;

    private String author;
    private int pageCount;
    private String title;
}
