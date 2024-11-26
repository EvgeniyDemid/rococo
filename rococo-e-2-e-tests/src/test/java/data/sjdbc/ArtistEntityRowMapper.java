package data.sjdbc;

import data.entity.ArtistEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ArtistEntityRowMapper implements RowMapper<ArtistEntity> {

	public static final ArtistEntityRowMapper instance = new ArtistEntityRowMapper();

	public ArtistEntityRowMapper() {
	}

	@Override
	public ArtistEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		ArtistEntity artistEntity = new ArtistEntity();
		artistEntity.setId((UUID) rs.getObject("id"));
		artistEntity.setName(rs.getString("name"));
		artistEntity.setBiography(rs.getString("biography"));
		artistEntity.setPhoto(rs.getString("photo"));
		return artistEntity;
	}
}
