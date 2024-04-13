package com.example.heroes.dto;

import jakarta.validation.constraints.NotNull;
public record HeroDto(@NotNull Long id, @NotNull String displayName) {
}
