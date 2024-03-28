package org.acme.dto;

import jakarta.enterprise.context.RequestScoped;
import org.acme.entities.Book;

@RequestScoped
public class BookMapperImpl implements BookMapper {
    @Override
    public Book fromCreate(CreateBookRecord bookDTO) {
        Book b = new Book();
        b.setName(bookDTO.name());
        b.setGenre(bookDTO.genre());
        b.setDescription(bookDTO.description());
        b.setNumPages(bookDTO.numPages());
        b.setPubDate(bookDTO.pubDate());
        return b;
    }
}
