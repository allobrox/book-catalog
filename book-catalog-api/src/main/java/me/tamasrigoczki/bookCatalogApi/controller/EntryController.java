package me.tamasrigoczki.bookCatalogApi.controller;

import lombok.RequiredArgsConstructor;
import me.tamasrigoczki.bookCatalogApi.model.dto.BookEntryDto;
import me.tamasrigoczki.bookCatalogApi.model.entity.BookEntry;
import me.tamasrigoczki.bookCatalogApi.service.BookEntryService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/entry")
@RequiredArgsConstructor
public class EntryController {

    private final BookEntryService bookEntryService;
    private final ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookEntryDto> createBook(@RequestBody
                                                   BookEntryDto bookEntryDto) {

        BookEntry bookEntry = modelMapper.map(bookEntryDto, BookEntry.class);
        BookEntry savedEntry = bookEntryService.saveBookEntry(bookEntry);
        BookEntryDto dto = modelMapper.map(savedEntry, BookEntryDto.class);

        return ResponseEntity.ok(dto);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookEntryDto>> getEntriesForBook(
            @RequestParam("bookId") String bookId) {
        return ResponseEntity.ok(bookEntryService.getByBookId(bookId).stream()
                .map(entity -> modelMapper.map(entity, BookEntryDto.class))
                .collect(
                        Collectors.toList()));
    }
}
