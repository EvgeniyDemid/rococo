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
	public String authUrl() {
		return "http://auth.niffler.dc:9000/";
	}

	@Override
	public String gatewayUrl() {
		return null;
	}

	@Override
	public int dbPort() {
		return Config.super.dbPort();
	}
}
