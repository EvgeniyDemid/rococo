package data.repository;

import data.DataBase;
import data.entity.ArtistEntity;
import data.sjdbc.ArtistEntityRowMapper;
import data.sjdbc.DataSourceProvider;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Optional;
import java.util.UUID;

public class ArtistRepositorySpringJdbc implements ArtistRepository {

	public static JdbcTemplate jdbcArtistTemplate = new JdbcTemplate(DataSourceProvider.dataSource(DataBase.ARTIST));

	@Override
	public ArtistEntity createArtist(ArtistEntity artistEntity) {
		KeyHolder kh = new GeneratedKeyHolder();
		jdbcArtistTemplate.update(con -> {
					PreparedStatement ps = con.prepareStatement(
							"INSERT INTO artist (name,biography) VALUES(?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
					);

						ps.setString(1, artistEntity.getName());
						ps.setString(2, artistEntity.getBiography());
						return ps;
				}, kh
		);
		artistEntity.setId((UUID) kh.getKeys().get("id"));
		return artistEntity;
	}

	@Override
	public ArtistEntity updateArtist(ArtistEntity artistEntity) {
		jdbcArtistTemplate.update(
			"UPDATE artist SET \"name\" = ?, biography=?, photo=? WHERE id=?",
				artistEntity.getName(),
				artistEntity.getBiography(),
				artistEntity.getPhoto(),
				artistEntity.getId()
		);
		return artistEntity;
	}

	@Override
	public ArtistEntity findArtistById(UUID id) {
		Optional<ArtistEntity> artist = Optional.ofNullable(jdbcArtistTemplate.queryForObject(
				"SELECT * FROM artist WHERE id = ?",
				ArtistEntityRowMapper.instance,
				id
		));
		return artist.orElse(null);
	}

	@Override
	public void deleteArtist(UUID id) {
		jdbcArtistTemplate.update(
				"DELETE  FROM artist  WHERE id = ?",
				id
		);
	}
}
