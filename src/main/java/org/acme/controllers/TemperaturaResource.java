package org.acme.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entities.Temperatura;
import org.acme.services.TemperaturaService;

import java.util.*;

@Path("/temperaturas")
public class TemperaturaResource {

    private TemperaturaService service;

    @Inject
    public TemperaturaResource(TemperaturaService service) {
        this.service = service;
    }

    @POST
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    public Temperatura nueva(Temperatura temp){
        service.addTemperatura(temp);
        return temp;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Temperatura> allTemp(){
        return service.obtainTemperaturas();
    }

    @GET
    @Path("/maximas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allTempMaximas(){
        if(service.maximas().isEmpty()){
            return Response.status(204).entity("No hay temperaturas listadas").build();
        } else {
            return Response.ok(service.maximas()).build();
        }
    }

    @GET
    @Path("/ciudad/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public Temperatura tempXcity(@PathParam("city") String city){
        return service.sacarTemperatura(city).orElseThrow(()-> new NoSuchElementException("No hay registro para la ciuad" + city));
    }


    @GET
    @Path("/ciudad2/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public Temperatura tempXcity2(@PathParam("city") String city){
        return service.sacarTemperatura(city).get();
    }


}
