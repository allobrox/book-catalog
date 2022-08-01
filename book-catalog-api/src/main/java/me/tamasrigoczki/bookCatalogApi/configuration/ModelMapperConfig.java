package me.tamasrigoczki.bookCatalogApi.configuration;

import me.tamasrigoczki.bookCatalogApi.model.dto.BookEntryDto;
import me.tamasrigoczki.bookCatalogApi.model.entity.Book;
import me.tamasrigoczki.bookCatalogApi.model.entity.BookEntry;
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
                .setConverter(createEntryDtoToEntityConverter());
        modelMapper.createTypeMap(BookEntry.class, BookEntryDto.class)
                .setConverter(createEntryEntityToDtoConverter());
    }

    private Converter<BookEntryDto, BookEntry> createEntryDtoToEntityConverter() {
        return context -> {
            BookEntry bookEntry = new BookEntry();
            BookEntryDto dto = context.getSource();
            try {
                Book book = new Book();
                book.setId(dto.getBookId());
                bookEntry.setBook(book);
                bookEntry.setCreated_at(
                        new SimpleDateFormat("yyyy-MM-dd").parse(
                                dto.getDay()));
                bookEntry.setProgress(dto.getProgress());
                bookEntry.setProgressType(dto.getProgressType());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return bookEntry;
        };
    }

    private Converter<BookEntry, BookEntryDto> createEntryEntityToDtoConverter() {
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
}
