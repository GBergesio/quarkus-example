package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Temperatura;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class TemperaturaService {

    private List<Temperatura> valores = new ArrayList<>();

    public void addTemperatura(Temperatura t){
        valores.add(t);
    }

    public List<Temperatura> obtainTemperaturas(){
        return Collections.unmodifiableList(valores);
    }

    public List<Integer> maximas(){
        return valores.stream().map(Temperatura::getMaxima).collect(Collectors.toList());
    }

    public Optional<Temperatura> sacarTemperatura(String city){
        return valores.stream().filter(t-> t.getCiudad().equals(city)).findAny();
    }
}
