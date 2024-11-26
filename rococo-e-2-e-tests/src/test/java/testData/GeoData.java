package testData;

import model.GeoJson;

public class GeoData {
	public static GeoJson[] geoData = new GeoJson[]{
			GeoJson.
					builder().
					id(null).
					city("Милано").
					country(CountryData.countryData[0]).
					build(),
			GeoJson.builder().
					id(null).
					city("Санкт-Петербург").
					country(CountryData.countryData[1]).
					build(),
			GeoJson.builder().
					id(null).
					city("Париж").
					country(CountryData.countryData[2]).
					build()
	};
}
