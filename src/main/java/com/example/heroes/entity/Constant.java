package com.example.heroes.entity;

import com.example.heroes.dto.HeroDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Constant {
    List<HeroDto> heroes;
}
