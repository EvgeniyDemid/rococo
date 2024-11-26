package data.repository;

import data.DataBase;
import data.entity.CountryEntity;
import data.sjdbc.CountryEntityRowMapper;
import data.sjdbc.DataSourceProvider;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.Optional;
import java.util.UUID;

public class CountryRepositorySpringJdbc implements CountryRepository {

	public static JdbcTemplate jdbcCountryTemplate = new JdbcTemplate(DataSourceProvider.dataSource(DataBase.COUNTRY));

	@Override
	public CountryEntity createCountry(CountryEntity countryEntity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcCountryTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO country (name) VALUES(?)",
					PreparedStatement.RETURN_GENERATED_KEYS
			);
			ps.setString(1, countryEntity.getName());
			return ps;
		}, keyHolder);
		countryEntity.setId((UUID) keyHolder.getKeys().get("id"));
		return countryEntity;
	}

	@Override
	public CountryEntity updateCountry(CountryEntity countryEntity) {
		jdbcCountryTemplate.update(
				"UPDATE country SET \"name\"=? WHERE id=?",
				countryEntity.getName(),
				countryEntity.getId()
		);
		return countryEntity;
	}

	@Override
	public CountryEntity findCountryById(UUID id) {
		Optional<CountryEntity> country =
				Optional.ofNullable(jdbcCountryTemplate.queryForObject(
						"SELECT id, \"name\" FROM country WHERE id=?",
						CountryEntityRowMapper.instance,
						id
				));
		return country.orElse(null);
	}

	@Override
	public void deleteCountry(UUID id) {
		jdbcCountryTemplate.update(
				"DELETE FROM country WHERE id=?",
				id);
	}
}
