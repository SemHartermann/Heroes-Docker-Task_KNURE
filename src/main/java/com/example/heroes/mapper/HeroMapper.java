package com.example.heroes.mapper;

import com.example.heroes.dto.HeroDto;
import com.example.heroes.entity.Hero;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HeroMapper {
    HeroMapper MAPPER = Mappers.getMapper(HeroMapper.class);
    HeroDto toDto(Hero entity);

    Hero toEntity(HeroDto dto);

    List<Hero> toEntityList(List<HeroDto> heroDtos);
    void updateEntity(HeroDto dto, @MappingTarget Hero entity);
}