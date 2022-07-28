package me.tamasrigoczki.bookCatalogApi.model.dto.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookAlreadyExistsError {
    private String id;
}
