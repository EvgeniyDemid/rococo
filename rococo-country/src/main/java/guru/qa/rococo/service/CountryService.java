package guru.qa.rococo.service;

import guru.qa.rococo.data.CountryEntity;
import guru.qa.rococo.data.repository.CountryRepository;
import guru.qa.rococo.ex.NotFoundException;
import guru.qa.rococo.model.CountryJson;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    public @Nonnull Page<CountryJson> getAll(@Nullable String name, @Nonnull Pageable pageable) {
        Page<CountryEntity> countries = (name == null)
                ? countryRepository.findAll(pageable)
                : countryRepository.findAllByNameContainsIgnoreCase(name, pageable);
        return countries.map(CountryJson::fromEntity);
    }

    public @Nonnull CountryJson findCountryById(@Nonnull String id) {
        return CountryJson.fromEntity(
                countryRepository.findById(
                        UUID.fromString(id)
                ).orElseThrow(
                        () -> new NotFoundException("Страна не найдена по id: " + id)
                )
        );
    }
}
