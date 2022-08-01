package me.tamasrigoczki.bookCatalogApi.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.tamasrigoczki.bookCatalogApi.model.enums.ProgressType;

@RequiredArgsConstructor
@Getter
@Setter
public class BookEntryDto {

    private Long id;
    private String bookId;
    private String day;
    private int progress;
    private ProgressType progressType;
}
