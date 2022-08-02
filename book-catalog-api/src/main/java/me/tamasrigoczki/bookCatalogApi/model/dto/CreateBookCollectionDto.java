package me.tamasrigoczki.bookCatalogApi.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Getter
@Setter
public class CreateBookCollectionDto {

    @NotBlank(message = "name is mandatory")
    private String name;
}
