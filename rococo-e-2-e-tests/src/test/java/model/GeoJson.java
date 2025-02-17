package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import testData.RandomData;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeoJson {

	@JsonProperty("id")
	UUID id;
	@JsonProperty("city")
	String city;
	@JsonProperty("country")
	CountryJson country;

	public GeoJson random() {
		RandomData randomData = new RandomData();

		return new GeoJson(
				null,
				randomData.randomCity(),
				new CountryJson().random()
		);
	}
}
