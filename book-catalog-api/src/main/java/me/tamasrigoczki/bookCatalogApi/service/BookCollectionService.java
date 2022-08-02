package me.tamasrigoczki.bookCatalogApi.service;

import lombok.AllArgsConstructor;
import me.tamasrigoczki.bookCatalogApi.model.dto.BookCollectionDto;
import me.tamasrigoczki.bookCatalogApi.model.dto.BookSampleDto;
import me.tamasrigoczki.bookCatalogApi.model.entity.Book;
import me.tamasrigoczki.bookCatalogApi.model.entity.BookCollection;
import me.tamasrigoczki.bookCatalogApi.repository.BookCollectionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BookCollectionService {

    private final BookCollectionRepository bookCollectionRepository;
    private final BookService bookService;
    private final ModelMapper modelMapper;

    public BookCollection createBookCollection(BookCollection bookCollection) {
        return bookCollectionRepository.save(bookCollection);
    }

    public BookCollection getById(Long id) {
        return bookCollectionRepository.findById(id).orElseThrow();
    }

    public BookCollectionDto getByIdWithBooks(Long id) {
        BookCollection bookCollection = getById(id);
        List<Book> books = bookService.getByCollectionId(id);
        List<BookSampleDto> bookSamples =
                books.stream().map(book -> modelMapper.map(book,
                        BookSampleDto.class)).collect(Collectors.toList());
        BookCollectionDto bookCollectionDto =
                modelMapper.map(bookCollection, BookCollectionDto.class);
        bookCollectionDto.setBooks(bookSamples);

        return bookCollectionDto;
    }
}
