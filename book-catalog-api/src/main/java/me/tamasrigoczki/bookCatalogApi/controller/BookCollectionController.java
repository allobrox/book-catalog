package me.tamasrigoczki.bookCatalogApi.controller;

import lombok.RequiredArgsConstructor;
import me.tamasrigoczki.bookCatalogApi.model.dto.BookCollectionDto;
import me.tamasrigoczki.bookCatalogApi.model.dto.CreateBookCollectionDto;
import me.tamasrigoczki.bookCatalogApi.model.entity.BookCollection;
import me.tamasrigoczki.bookCatalogApi.service.BookCollectionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/collection")
@RequiredArgsConstructor
public class BookCollectionController {

    private final BookCollectionService bookCollectionService;
    private final ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookCollectionDto> createBookCollection(
            @Valid @RequestBody
            CreateBookCollectionDto createBookCollectionDto) {
        BookCollection bookCollection =
                modelMapper.map(createBookCollectionDto, BookCollection.class);
        BookCollection savedBookCollection =
                bookCollectionService.createBookCollection(bookCollection);
        BookCollectionDto responseBody = modelMapper.map(savedBookCollection,
                BookCollectionDto.class);

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookCollectionDto> getBookCollectionById(
            @PathVariable Long id) {
        return ResponseEntity.ok(bookCollectionService.getByIdWithBooks(id));
    }
}
