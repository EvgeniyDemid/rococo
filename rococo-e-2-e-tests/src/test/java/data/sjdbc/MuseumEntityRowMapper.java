package data.sjdbc;

import data.entity.MuseumEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MuseumEntityRowMapper implements RowMapper<MuseumEntity> {

	public static final MuseumEntityRowMapper instance = new MuseumEntityRowMapper();

	@Override
	public MuseumEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		MuseumEntity museum = new MuseumEntity();
		museum.setId((UUID) rs.getObject("id"));
		museum.setTitle(rs.getString("title"));
		museum.setDescription(rs.getString("description"));
		museum.setCity(rs.getString("city"));
		museum.setPhoto(rs.getString("photo"));
		museum.setCountryId((UUID) rs.getObject("country_id"));
		return museum;
	}
}
