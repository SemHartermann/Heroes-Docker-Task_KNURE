package com.example.heroes.dto;

import com.example.heroes.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(of = {"id"})
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HeroDto {
    Long id;
    String displayName;
}
