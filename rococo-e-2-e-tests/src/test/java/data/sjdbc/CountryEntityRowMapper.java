package data.sjdbc;

import data.entity.CountryEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CountryEntityRowMapper implements RowMapper<CountryEntity> {

	public static final CountryEntityRowMapper instance = new CountryEntityRowMapper();

	@Override
	public CountryEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		CountryEntity country = new CountryEntity();
		country.setId((UUID) rs.getObject("id"));
		country.setName(rs.getString("name"));
		return country;
	}
}
