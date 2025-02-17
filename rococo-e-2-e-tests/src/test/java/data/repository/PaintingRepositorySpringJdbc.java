package data.repository;

import data.DataBase;
import data.entity.PaintingEntity;
import data.sjdbc.DataSourceProvider;
import data.sjdbc.PaintingEntityRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.Optional;
import java.util.UUID;

public class PaintingRepositorySpringJdbc implements PaintingRepository {
	private static final JdbcTemplate jdbcPaintingTemplate = new JdbcTemplate(
			DataSourceProvider.dataSource(DataBase.PAINTING));

	@Override
	public PaintingEntity createPainting(PaintingEntity paintingEntity) {
		KeyHolder kh = new GeneratedKeyHolder();
		jdbcPaintingTemplate.update(con -> {
					PreparedStatement ps = con.prepareStatement(
							"INSERT INTO painting (title,description,museum_id,artist_id)" +
									" VALUES(?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
					);
					ps.setString(1, paintingEntity.getTitle());
					ps.setString(2, paintingEntity.getDescription());
					ps.setObject(3, paintingEntity.getMuseumId());
					ps.setObject(4, paintingEntity.getArtistId());
					return ps;
				}, kh
		);
		paintingEntity.setId((UUID) kh.getKeys().get("id"));
		return paintingEntity;
	}

	@Override
	public PaintingEntity updatePainting(PaintingEntity paintingEntity) {
		jdbcPaintingTemplate.update(
				"UPDATE painting SET title=?, description=?, artist_id=?, museum_id=?, \"content\"=? WHERE id=?",
				paintingEntity.getTitle(),
				paintingEntity.getDescription(),
				paintingEntity.getArtistId(),
				paintingEntity.getMuseumId(),
				paintingEntity.getContent(),
				paintingEntity.getId()
		);
		return paintingEntity;
	}

	@Override
	public PaintingEntity findByIdPainting(UUID id) {
		Optional<PaintingEntity> painting = Optional.ofNullable(
				jdbcPaintingTemplate.queryForObject(
						"SELECT id, title, description, artist_id, museum_id, \"content\" FROM painting WHERE id=?",
						PaintingEntityRowMapper.instance,
						id
				)
		);
		return painting.orElse(null);
	}

	@Override
	public void deletePaintingById(UUID id) {
		jdbcPaintingTemplate.update("DELETE FROM painting WHERE id=?", id);
	}

	@Override
	public void deletePaintingByMuseumId(UUID id) {
		jdbcPaintingTemplate.update("DELETE FROM painting WHERE museum_id=?", id);
	}

	@Override
	public void deletePaintingByArtistId(UUID id) {
		jdbcPaintingTemplate.update("DELETE FROM painting WHERE artist_id=?", id);
	}
}
