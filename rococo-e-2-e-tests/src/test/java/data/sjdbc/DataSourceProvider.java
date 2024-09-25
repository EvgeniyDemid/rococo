package data.sjdbc;

import com.p6spy.engine.spy.P6DataSource;
import data.DataBase;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum DataSourceProvider {

	INSTANCE;

	private final Map<DataBase, DataSource> store = new ConcurrentHashMap<>();

	private DataSource computeDataSource(DataBase db) {
		return store.computeIfAbsent(db, key -> {
			PGSimpleDataSource pgDataSource = new PGSimpleDataSource();
			pgDataSource.setURL(db.getJdbcUrl());
			pgDataSource.setUser("postgres");
			pgDataSource.setPassword("secret");
			return new P6DataSource(pgDataSource);
		});
	}

	public static DataSource dataSource(DataBase db) {
		return DataSourceProvider.INSTANCE.computeDataSource(db);
	}

}
