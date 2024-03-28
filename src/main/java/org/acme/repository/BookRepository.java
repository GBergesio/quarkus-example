package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Book;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {

}
