package me.tamasrigoczki.bookCatalogApi.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Getter
@Setter
public class BookDto {

    @NotBlank(message = "Book ID is mandatory")
    private String bookId;
    @NotBlank(message = "Author is mandatory")
    private String author;
    @Min(value = 1, message = "Pages must be greater than 0")
    private int pages;
    @NotBlank(message = "Title is mandatory")
    private String title;
}
