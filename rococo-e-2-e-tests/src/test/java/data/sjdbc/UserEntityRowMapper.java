package data.sjdbc;

import data.entity.UserEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserEntityRowMapper implements RowMapper<UserEntity> {

	public static final UserEntityRowMapper instance = new UserEntityRowMapper();

	public UserEntityRowMapper() {
	}

	@Override
	public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserEntity userEntity = new UserEntity();
		userEntity.setId((UUID) rs.getObject("id"));
		userEntity.setUsername(rs.getString("username "));
		userEntity.setFirstname(rs.getString("firstname"));
		userEntity.setLastname(rs.getString("lastname"));
		userEntity.setAvatar(rs.getBytes("avatar"));
		return userEntity;
	}
}
