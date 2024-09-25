package data.repository;

import data.DataBase;
import data.entity.UserAuthEntity;
import data.entity.UserEntity;
import data.sjdbc.DataSourceProvider;
import data.sjdbc.UserEntityRowMapper;
import enums.Authority;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class UserRepositoryStringJdbc implements UserRepository {

	private static final PasswordEncoder pe = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	public static JdbcTemplate jdbcAuthTemplate = new JdbcTemplate(DataSourceProvider.dataSource(DataBase.AUTH));
	public static JdbcTemplate jdbcUDTemplate = new JdbcTemplate(DataSourceProvider.dataSource(DataBase.USERDATA));
	public static TransactionTemplate authTxTemplate =
			new TransactionTemplate(new JdbcTransactionManager(DataSourceProvider.dataSource(DataBase.AUTH)));

	@Override
	public UserAuthEntity createUserInAuth(UserAuthEntity userAuthEntity) {
		return authTxTemplate.execute(status -> {
			KeyHolder kh = new GeneratedKeyHolder();
			jdbcAuthTemplate.update(con -> {
						PreparedStatement ps = con.prepareStatement(
								"INSERT INTO \"user\" (" +
										"username, password, enabled, account_non_expired, account_non_locked, credentials_non_expired)" +
										" VALUES (?, ?, ?, ?, ?, ?)",
								PreparedStatement.RETURN_GENERATED_KEYS
						);
						ps.setString(1, userAuthEntity.getUsername());
						ps.setString(2, pe.encode(userAuthEntity.getPassword()));
						ps.setBoolean(3, userAuthEntity.getEnabled());
						ps.setBoolean(4, userAuthEntity.getAccountNonExpired());
						ps.setBoolean(5, userAuthEntity.getAccountNonLocked());
						ps.setBoolean(6, userAuthEntity.getCredentialsNonExpired());
						return ps;
					}, kh
			);
			userAuthEntity.setId((UUID) kh.getKeys().get("id"));

			jdbcAuthTemplate.batchUpdate(
					"INSERT INTO \"authority\" (user_id, authority) VALUES (?, ?)",
					new BatchPreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps, int i) throws SQLException {
							ps.setObject(1, userAuthEntity.getId());
							ps.setString(2, Authority.values()[i].name());
						}

						@Override
						public int getBatchSize() {
							return userAuthEntity.getAuthorities().size();
						}
					}
			);
			return userAuthEntity;
		});
	}

	@Override
	public UserEntity createUserInUserdata(UserEntity userEntity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcUDTemplate.update(con -> {
					PreparedStatement ps = con.prepareStatement(
							"INSERT INTO \"user\" (" +
									"username, firstname,lastname,avatar) VALUES(?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
					);
					ps.setString(1, userEntity.getUsername());
					ps.setString(2, userEntity.getFirstname());
					ps.setString(3, userEntity.getLastname());
					ps.setObject(4, userEntity.getAvatar());
					return ps;
				}, keyHolder
		);
		userEntity.setId((UUID) keyHolder.getKeys().get("id"));
		return userEntity;
	}

	@Override
	public Optional<UserEntity> findUserInUserdataById(UUID id) {
		try {
			return Optional.of(
					jdbcUDTemplate.queryForObject(
							"SELECT * FROM \"user\" WHERE id = ?",
							UserEntityRowMapper.instance,
							id
					)
			);
		} catch (DataRetrievalFailureException e) {
			return Optional.empty();
		}
	}
}
