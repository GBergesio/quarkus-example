package org.acme.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    @JsonProperty("genero")
    @JsonAlias({"genero", "genre"})
    private String genre;

    private int numPages;

    private LocalDate pubDate;

    private String description;

    @CreationTimestamp
    @JsonIgnore
    private LocalDate createDate;

    @UpdateTimestamp
    @JsonIgnore
    private LocalDate updateDate;

}
