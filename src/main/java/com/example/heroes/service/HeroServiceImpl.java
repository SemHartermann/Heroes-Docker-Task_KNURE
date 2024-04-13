package com.example.heroes.service;

import com.example.heroes.dto.HeroDto;
import com.example.heroes.entity.Hero;
import com.example.heroes.mapper.HeroMapper;
import com.example.heroes.repository.HeroRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HeroServiceImpl implements HeroService {
    HttpGraphQlClient graphQlClient;
    HeroRepository heroRepository;
    HeroMapper heroMapper;

    @Override
    public Page<HeroDto> downloadHeroesFromAPI(Pageable page) {
        List<HeroDto> heroDtos = getHeroesFromAPI();

        List<Hero> downloadedHeroes = heroMapper.toEntityList(heroDtos);

        heroRepository.deleteAllInBatch(downloadedHeroes);
        heroRepository.saveAll(downloadedHeroes);

        Page<Hero> heroes = heroRepository.findAll(page);

        return heroes.map(heroMapper::toDto);
    }

    @Override
    public List<HeroDto> getHeroesFromAPI() {
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
    public Page<HeroDto> getAllHeroes(Pageable page) {
        return null;
    }

    @Override
    public Optional<HeroDto> getHeroById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<HeroDto> addHero(HeroDto hero) {
        return Optional.empty();
    }

    @Override
    public Optional<HeroDto> updateHeroById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteHeroById(Long id) {

    }
}
