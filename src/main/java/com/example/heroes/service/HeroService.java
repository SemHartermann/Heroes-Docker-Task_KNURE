package com.example.heroes.service;

import com.example.heroes.dto.HeroDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HeroService {
    Page<HeroDto> downloadHeroesFromAPI(Pageable pageable);

    Page<HeroDto> getHeroesFromAPI(Pageable pageable);

    Page<HeroDto> getAllHeroes(Pageable pageable);

    HeroDto getHeroById(Long id);

    HeroDto updateHeroById(Long id, HeroDto heroDto) throws EntityNotFoundException;

    void deleteHeroById(Long id);

    Page<HeroDto> getCurrentHeroes(Pageable pageable);
}
