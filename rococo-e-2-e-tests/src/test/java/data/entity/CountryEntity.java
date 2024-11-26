package data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.CountryJson;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryEntity {
	private UUID id;
	private String name;

	public CountryEntity fromJson(CountryJson countryJson) {
		return new CountryEntity(countryJson.getId(), countryJson.getName());
	}
}
