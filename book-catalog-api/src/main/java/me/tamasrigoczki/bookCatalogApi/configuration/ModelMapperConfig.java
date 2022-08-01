package me.tamasrigoczki.bookCatalogApi.configuration;

import me.tamasrigoczki.bookCatalogApi.model.dto.BookEntryDto;
import me.tamasrigoczki.bookCatalogApi.model.dto.CreateBookEntryDto;
import me.tamasrigoczki.bookCatalogApi.model.entity.Book;
import me.tamasrigoczki.bookCatalogApi.model.entity.BookEntry;
import me.tamasrigoczki.bookCatalogApi.model.enums.ProgressType;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        configureModelMapper(modelMapper);
        return modelMapper;
    }

    private void configureModelMapper(ModelMapper modelMapper) {
        modelMapper.createTypeMap(BookEntryDto.class, BookEntry.class)
                .setConverter(entryDtoToEntityConverter());
        modelMapper.createTypeMap(BookEntry.class, BookEntryDto.class)
                .setConverter(entryEntityToDtoConverter());
        modelMapper.createTypeMap(CreateBookEntryDto.class, BookEntry.class)
                .setConverter(createEntryDtoToEntityConverter());
    }

    private Converter<BookEntryDto, BookEntry> entryDtoToEntityConverter() {
        return context -> {
            BookEntryDto dto = context.getSource();
            return bookEntryConverter(dto.getId(), dto.getBookId(),
                    dto.getDay(),
                    dto.getProgress(), dto.getProgressType());
        };
    }

    private Converter<BookEntry, BookEntryDto> entryEntityToDtoConverter() {
        return context -> {
            BookEntryDto dto = new BookEntryDto();
            BookEntry bookEntry = context.getSource();
            dto.setId(bookEntry.getId());
            dto.setBookId(bookEntry.getBook().getId());
            dto.setDay(new SimpleDateFormat("yyyy-MM-dd").format(
                    bookEntry.getCreated_at()));
            dto.setProgress(bookEntry.getProgress());
            dto.setProgressType(bookEntry.getProgressType());

            return dto;
        };
    }

    private Converter<CreateBookEntryDto, BookEntry> createEntryDtoToEntityConverter() {
        return context -> {
            CreateBookEntryDto dto = context.getSource();
            return bookEntryConverter(null, dto.getBookId(),
                    dto.getDay(),
                    dto.getProgress(),
                    dto.getProgressType());
        };
    }

    private BookEntry bookEntryConverter(
            Long entryId, String bookId,
            String day, int progress,
            ProgressType progressType) {
        BookEntry bookEntry = new BookEntry();
        try {
            Book book = new Book();
            book.setId(bookId);
            bookEntry.setId(entryId);
            bookEntry.setBook(book);
            bookEntry.setCreated_at(
                    new SimpleDateFormat("yyyy-MM-dd").parse(
                            day));
            bookEntry.setProgress(progress);
            bookEntry.setProgressType(progressType);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return bookEntry;
    }
}
