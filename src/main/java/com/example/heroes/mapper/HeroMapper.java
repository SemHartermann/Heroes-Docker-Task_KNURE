package com.example.heroes.mapper;

import com.example.heroes.dto.HeroDto;
import com.example.heroes.entity.Hero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HeroMapper {
    HeroMapper MAPPER = Mappers.getMapper(HeroMapper.class);

    @Mapping(target = "lessonId", source = "entity.lesson.id")
    @Mapping(target = "studentId", source = "entity.student.id")
    HeroDto toDto(Hero entity);

    @Mapping(target = "lesson", ignore = true)
    @Mapping(target = "student", ignore = true)
    Hero toEntity(HeroDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lesson", ignore = true)
    @Mapping(target = "student", ignore = true)
    void updateEntity(HeroDto dto, @MappingTarget Hero entity);
}