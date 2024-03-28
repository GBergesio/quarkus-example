package org.acme.dto;
import org.acme.entities.Book;

public interface BookMapper {
    Book fromCreate(CreateBookRecord bookDTO);
}
