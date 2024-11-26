package data.repository;

import data.entity.CountryEntity;

import java.util.UUID;

public interface CountryRepository {

	CountryEntity createCountry(CountryEntity countryEntity);

	CountryEntity updateCountry (CountryEntity countryEntity);

	CountryEntity findCountryById (UUID id);

	void deleteCountry (UUID id);
}
