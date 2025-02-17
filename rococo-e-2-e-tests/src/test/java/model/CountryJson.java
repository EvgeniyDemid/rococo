package model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CountryJson {

	@JsonProperty("id")
	UUID id;
	@JsonProperty("name")
	String name;

	public CountryJson random() {
		RandomData randomData = new RandomData();

		return new CountryJson(
				null,
				randomData.randomCountry()
		);
	}
}
