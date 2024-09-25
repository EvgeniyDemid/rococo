package config;

public class DockerConfig implements Config {

	static final DockerConfig instance = new DockerConfig();

	private DockerConfig() {
	}

	@Override
	public String frontUrl() {
		return "frontend.rococo.dc";
	}

	@Override
	public String dbHost() {
		return "rococo-all-db";
	}

	@Override
	public int dbPort() {
		return Config.super.dbPort();
	}
}
