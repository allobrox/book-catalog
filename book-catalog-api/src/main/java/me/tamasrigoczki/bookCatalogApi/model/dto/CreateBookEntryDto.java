package me.tamasrigoczki.bookCatalogApi.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.tamasrigoczki.bookCatalogApi.model.enums.ProgressType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Getter
@Setter
public class CreateBookEntryDto {

    @NotBlank(message = "bookId is mandatory")
    private String bookId;
    @NotBlank(message = "day is mandatory (yyyy-MM-dd)")
    private String day;
    @Min(value = 0, message = "progress must be at least 0")
    private int progress;
    @NotBlank(message = "progressType is mandatory")
    private ProgressType progressType;
}
