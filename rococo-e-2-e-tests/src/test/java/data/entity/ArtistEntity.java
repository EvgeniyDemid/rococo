package data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.ArtistJson;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistEntity {

	private UUID id;
	private String name;
	private String biography;
	private String photo;

	public ArtistEntity fromJson(ArtistJson artistJson) {
		return new ArtistEntity(
				artistJson.getId(),
				artistJson.getName(),
				artistJson.getBiography(),
				artistJson.getPhoto()
		);
	}
}
