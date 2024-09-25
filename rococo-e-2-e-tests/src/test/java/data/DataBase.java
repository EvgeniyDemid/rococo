package data;

import config.Config;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DataBase {
	ARTIST("jdbc:postgresql://%s:%d/rococo-artist"),
	AUTH("jdbc:postgresql://%s:%d/rococo-auth"),
	COUNTRY("jdbc:postgresql://%s:%d/rococo-country"),
	MUSEUM("jdbc:postgresql://%s:%d/rococo-museum"),
	PAINTING("jdbc:postgresql://%s:%d/rococo-painting"),
	USERDATA("jdbc:postgresql://%s:%d/rococo-userdata");

	public String getJdbcUrl() {
		return String.format(jdbcUrl, CFG.dbHost(), CFG.dbPort());
	}

	private final String jdbcUrl;

	private final Config CFG = Config.getInstance();
}
