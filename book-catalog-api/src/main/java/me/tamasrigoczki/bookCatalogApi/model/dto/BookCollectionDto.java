package me.tamasrigoczki.bookCatalogApi.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class BookCollectionDto {

    @NotBlank(message = "id is mandatory")
    private Long id;
    @NotBlank(message = "name is mandatory")
    private String name;
    private List<BookSampleDto> books;
}
