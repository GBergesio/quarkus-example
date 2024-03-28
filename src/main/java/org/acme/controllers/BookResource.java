package org.acme.controllers;

import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.Response;
import org.acme.dto.BookMapper;
import org.acme.dto.CreateBookRecord;
import org.acme.entities.Book;
import org.acme.repository.BookRepository;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@Path("/books")
@Transactional
public class BookResource {

    private BookRepository repository;
    private BookMapper mapper;

    @Inject
    public BookResource(BookRepository repository, BookMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @GET
    public List<Book> index()  {
        return repository.listAll();
    }

    @POST
    public Book insert(Book book)  {
        repository.persist(book);
        return book;
    }

    @POST
    @Path("/new")
    public Response insert2(@Valid CreateBookRecord book)  {
        var entity = mapper.fromCreate(book);
        repository.persist(entity);
        return Response.created(URI.create("books/" + entity.getId())).entity(entity).build();
    }

    @GET
    @Path("{id}")
    public Book byId(@PathParam("id") Long id)  {
        Book book = repository.findById(id);
        if (book != null){
            return book;
        }
        throw new NoSuchElementException("Book not found");
    }

    @DELETE
    @Path("{id}")
    public String delete(@PathParam("id") Long id) {
        if (repository.deleteById(id)){
            return "Eliminado correctamente";
        } else{
           return  "No se ha podido borrar";
        }
    }

    @PUT
    @Path("{id}")
    public Book update(@PathParam("id") Long id,Book book)  {
        Book update = repository.findById(id);
        if (update != null){
            update.setName(book.getName());
            update.setDescription(book.getDescription());
            update.setNumPages(book.getNumPages());
            update.setCreateDate(book.getCreateDate());
            update.setGenre(book.getGenre());
            repository.persist(update);
            return update;
        }

        throw new NoSuchElementException("Book not found");
    }

    @GET
    @Path("/busqueda/paginas/{qty}")
    public List<Book> byQtyPages(@QueryParam("qty") int qty)  {
        return repository.list("numPages >= ?1", qty);
    }

    @GET
    @Path("/busqueda")
    public List<Book> byQuery(@QueryParam("query") String query)  {
        if (query == null){
            return repository.listAll(Sort.by("pubDate", Sort.Direction.Descending));
        } else{
            String filter = "%" + query + "%";
            Sort crit = Sort.by("pubDate", Sort.Direction.Ascending);

            return repository.list("name ILIKE ?1 OR description ILIKE ?2",crit, filter, filter);
        }
    }

}
