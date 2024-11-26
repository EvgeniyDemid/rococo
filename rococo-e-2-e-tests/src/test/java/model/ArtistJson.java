package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import data.entity.ArtistEntity;
import lombok.*;

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
}
