package org.acme.controllers;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.acme.entities.Book;
import org.acme.entities.Magazine;

import java.util.List;

@Path("/magazine")
@Transactional
public class MagazineResource {

    @GET
    public List<Magazine> index()  {
        return Magazine.listAll();
    }

    @POST
    public Magazine insert(Magazine maga)  {
        maga.persist();
        return maga;
    }
}
