package org.acme.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "magazine")
@NoArgsConstructor
@AllArgsConstructor
public class Magazine extends PanacheEntity {

    private String name;

    private int numPages;

    private LocalDate pubDate;

    private String description;
}
