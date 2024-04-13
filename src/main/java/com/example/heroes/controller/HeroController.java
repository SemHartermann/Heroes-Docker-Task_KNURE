package com.example.heroes.controller;

import com.example.heroes.dto.HeroDto;
import com.example.heroes.service.HeroService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/heroes")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HeroController {
    HeroService heroService;

    @GetMapping()
    public Page<HeroDto> getCurrentHeroes(@PageableDefault Pageable pageable) {
        return heroService.getCurrentHeroes(pageable);
    }

    @GetMapping("/fromApi")
    public Page<HeroDto> getHeroesFromAPI(@PageableDefault Pageable pageable) {
        return heroService.getHeroesFromAPI(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroDto> getHeroById(@PathVariable Long id) {
        HeroDto heroDto = heroService.getHeroById(id);
        return ResponseEntity.ok(heroDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HeroDto> updateHeroById(@PathVariable Long id, @RequestBody @Valid HeroDto heroDto) {
        HeroDto updatedHero = heroService.updateHeroById(id, heroDto);
        return ResponseEntity.ok(updatedHero);
    }

    @PostMapping("/downloadFromApi")
    public Page<HeroDto> downloadHeroesFromAPI(@PageableDefault Pageable pageable) {
        return heroService.downloadHeroesFromAPI(pageable);
    }
}