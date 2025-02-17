package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import data.entity.ArtistEntity;
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
public class ArtistJson {

	@JsonProperty("id")
	UUID id;
	@JsonProperty("name")
	String name;
	@JsonProperty("biography")
	String biography;
	@JsonProperty("photo")
	String photo;

	public ArtistJson fromArtistEntity(ArtistEntity artistEntity) {
		return new ArtistJson(
				artistEntity.getId(),
				artistEntity.getName(),
				artistEntity.getBiography(),
				artistEntity.getPhoto()
		);
	}

	public ArtistJson random() {
		RandomData randomData = new RandomData();
		return new ArtistJson(
				null,
				randomData.randomArtist(),
				randomData.randomBiography(),
				randomData.photoArtist()
		);
	}
}
