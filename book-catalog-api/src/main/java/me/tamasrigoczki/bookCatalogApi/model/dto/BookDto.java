package me.tamasrigoczki.bookCatalogApi.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.tamasrigoczki.bookCatalogApi.model.enums.BookStatus;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
@Setter
public class BookDto {

    @NotBlank(message = "id is mandatory")
    private String id;
    @NotBlank(message = "Author is mandatory")
    private String author;
    private Long collectionId;
    @Min(value = 1, message = "Pages must be greater than 0")
    private int pages;
    @NotNull
    private BookStatus status;
    @NotBlank(message = "Title is mandatory")
    private String title;
}
