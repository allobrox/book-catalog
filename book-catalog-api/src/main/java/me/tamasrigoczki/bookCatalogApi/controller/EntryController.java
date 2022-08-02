package me.tamasrigoczki.bookCatalogApi.controller;

import lombok.RequiredArgsConstructor;
import me.tamasrigoczki.bookCatalogApi.model.dto.BookEntryDto;
import me.tamasrigoczki.bookCatalogApi.model.dto.CreateBookEntryDto;
import me.tamasrigoczki.bookCatalogApi.model.entity.BookEntry;
import me.tamasrigoczki.bookCatalogApi.service.BookEntryService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<BookEntryDto> createEntry(@Valid @RequestBody
                                                    CreateBookEntryDto bookEntryDto) {

        BookEntry bookEntry = modelMapper.map(bookEntryDto, BookEntry.class);
        BookEntry savedEntry = bookEntryService.saveBookEntry(bookEntry);
        BookEntryDto dto = modelMapper.map(savedEntry, BookEntryDto.class);

        return ResponseEntity.ok(dto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void modifyEntry(@Valid @RequestBody BookEntryDto bookEntryDto) {
        bookEntryService.modifyBookEntry(modelMapper.map(bookEntryDto,
                BookEntry.class));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookEntryDto>> getEntriesForBook(
            @RequestParam("bookId") String bookId) {
        return ResponseEntity.ok(bookEntryService.getByBookId(bookId).stream()
                .map(entity -> modelMapper.map(entity, BookEntryDto.class))
                .collect(
                        Collectors.toList()));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookEntryDto> getEntryById(@PathVariable Long id) {
        return ResponseEntity.ok(modelMapper.map(bookEntryService.getById(id),
                BookEntryDto.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEntry(@PathVariable Long id) {
        bookEntryService.deleteEntry(id);
    }
}
