package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import data.entity.MuseumEntity;
import data.repository.CountryRepositorySpringJdbc;
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
public class MuseumJson {

	@JsonProperty("id")
	UUID id;
	@JsonProperty("title")
	String title;
	@JsonProperty("description")
	String description;
	@JsonProperty("photo")
	String photo;
	@JsonProperty("geo")
	GeoJson geo;

	public MuseumJson random() {
		RandomData randomData = new RandomData();
		return new MuseumJson(
				null,
				randomData.randomMuseum(),
				randomData.randomDescription(),
				randomData.photoMuseum(),
				new GeoJson().random()
		);
	}

	public MuseumJson fromEntity(MuseumEntity museum) {
		return new MuseumJson(
				museum.getId(),
				museum.getTitle(),
				museum.getDescription(),
				museum.getPhoto(),
				new GeoJson(
						null,
						museum.getCity(),
						new CountryJson(
								museum.getCountryId(),
								new CountryRepositorySpringJdbc().findCountryById(museum.getCountryId()).getName()
						)

				)
		);
	}
}
