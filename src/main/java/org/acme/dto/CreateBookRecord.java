package org.acme.dto;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CreateBookRecord(@NotBlank String name, String description, String genre, LocalDate pubDate, int numPages) {
}
