package com.example.heroes.service;

import com.example.heroes.dto.HeroDto;
import com.example.heroes.entity.Hero;
import com.example.heroes.mapper.HeroMapper;
import com.example.heroes.repository.HeroRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HeroServiceImpl implements HeroService {
    HttpGraphQlClient graphQlClient;

    HeroRepository heroRepository;

    HeroMapper heroMapper;

    @Override
    @Transactional
    public Page<HeroDto> downloadHeroesFromAPI(Pageable pageable) {
        List<HeroDto> heroDtos = getHeroesFromAPI();

        List<Hero> downloadedHeroes = heroMapper.toEntityList(heroDtos);

        heroRepository.saveAll(downloadedHeroes);

        Page<Hero> heroes = heroRepository.findAll(pageable);

        return heroes.map(heroMapper::toDto);
    }

    private List<HeroDto> getHeroesFromAPI() {
        String document = """
                query {
                   constants{
                     heroes(gameVersionId: 1, language: ENGLISH){
                       id
                 	   displayName
                     }
                   }
                }
                """;

        return graphQlClient.document(document)
                .retrieve("constants.heroes")
                .toEntityList(HeroDto.class)
                .block();
    }

    @Override
    public Page<HeroDto> getHeroesFromAPI(Pageable pageable) {
        List<HeroDto> allHeroesFromAPI = getHeroesFromAPI();

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), Objects.requireNonNull(allHeroesFromAPI).size());

        return new PageImpl<>(allHeroesFromAPI.subList(start, end), pageable, allHeroesFromAPI.size());
    }

    @Override
    public Page<HeroDto> getAllHeroes(Pageable pageable) {
        return heroRepository.findAll(pageable).map(heroMapper::toDto);
    }

    @Override
    public HeroDto getHeroById(Long id) {
        Hero hero = heroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hero not found with id " + id));

        return heroMapper.toDto(hero);
    }

    @Override
    public HeroDto updateHeroById(Long id, HeroDto heroDto) {
        Hero hero = heroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hero not found with id " + id));

        heroMapper.updateEntity(heroDto, hero);

        Hero savedHero = heroRepository.save(hero);

        return heroMapper.toDto(savedHero);
    }

    @Override
    public void deleteHeroById(Long id) {
        heroRepository.deleteById(id);
    }

    @Override
    public Page<HeroDto> getCurrentHeroes(Pageable pageable) {
        Page<Hero> heroes = heroRepository.findAll(pageable);

        return heroes.map(heroMapper::toDto);
    }
}
