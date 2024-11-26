package testData;

import model.CountryJson;

public class CountryData {

	public static CountryJson[] countryData = new CountryJson[]{
			CountryJson.
					builder().
					id(null).
					name("Италия").
					build(),
			CountryJson.
					builder().
					id(null).
					name("Россия").
					build(),
			CountryJson.
					builder().
					id(null).
					name("Франция").
					build()
	};
}
