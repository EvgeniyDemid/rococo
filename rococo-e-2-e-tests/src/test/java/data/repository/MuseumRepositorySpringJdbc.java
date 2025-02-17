package data.repository;

import data.DataBase;
import data.entity.MuseumEntity;
import data.sjdbc.DataSourceProvider;
import data.sjdbc.MuseumEntityRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.Optional;
import java.util.UUID;

public class MuseumRepositorySpringJdbc implements MuseumRepository {
	private static final JdbcTemplate jdbcMuseumTemplate = new JdbcTemplate(
			DataSourceProvider.dataSource(DataBase.MUSEUM));

	@Override
	public MuseumEntity createMuseum(MuseumEntity museumEntity) {
		KeyHolder kh = new GeneratedKeyHolder();
		jdbcMuseumTemplate.update(con -> {
					PreparedStatement ps = con.prepareStatement(
							"INSERT INTO museum (title,description,city,country_id)" +
									"VALUES(?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
					);
					ps.setString(1, museumEntity.getTitle());
					ps.setString(2, museumEntity.getDescription());
					ps.setString(3, museumEntity.getCity());
					ps.setObject(4, museumEntity.getCountryId());
					return ps;
				}, kh
		);
		museumEntity.setId((UUID) kh.getKeys().get("id"));
		return museumEntity;
	}

	@Override
	public MuseumEntity updateMuseum(MuseumEntity museumEntity) {
		jdbcMuseumTemplate.update(
				"UPDATE public.museum SET title = ? description=?, city=?, country_id=? WHERE id=?",
				museumEntity.getTitle(),
				museumEntity.getDescription(),
				museumEntity.getCity(),
				museumEntity.getCountryId(),
				museumEntity.getId()
		);
		return museumEntity;
	}

	@Override
	public MuseumEntity findByIdMuseum(UUID id) {
		Optional<MuseumEntity> museum = Optional.ofNullable(jdbcMuseumTemplate.queryForObject(
				"SELECT id, title, description, city, photo, country_id FROM public.museum WHERE id=?",
				MuseumEntityRowMapper.instance,
				id
		));
		return museum.orElse(null);
	}

	@Override
	public void deleteMuseum(UUID id) {
		jdbcMuseumTemplate.update(
				"DELETE FROM museum WHERE id = ?",
				id
		);
	}
	@Override
	public void deleteMuseumByName(String name) {
		jdbcMuseumTemplate.update(
				"DELETE FROM museum WHERE title = ?",
				name
		);
	}
}
