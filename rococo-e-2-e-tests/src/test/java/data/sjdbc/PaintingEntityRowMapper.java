package data.sjdbc;

import data.entity.PaintingEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PaintingEntityRowMapper implements RowMapper<PaintingEntity> {

	public static final PaintingEntityRowMapper instance = new PaintingEntityRowMapper();

	@Override
	public PaintingEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		PaintingEntity painting = new PaintingEntity();
		painting.setId((UUID) rs.getObject("id"));
		painting.setTitle(rs.getString("title"));
		painting.setDescription(rs.getString("description"));
		painting.setArtistId((UUID) rs.getObject("artist_id"));
		painting.setMuseumId((UUID) rs.getObject("museum_id"));
		painting.setContent(rs.getString("content"));
		return null;
	}
}