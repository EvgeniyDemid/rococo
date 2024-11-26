package data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.MuseumJson;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MuseumEntity implements Serializable {

	private UUID id;
	private String title;
	private String description;
	private String city;
	private String photo;
	private UUID countryId;

	public MuseumEntity fromJson(MuseumJson museumJson) {
		return new MuseumEntity(
				museumJson.getId(),
				museumJson.getTitle(),
				museumJson.getDescription(),
				museumJson.getGeo().getCity(),
				museumJson.getPhoto(),
				museumJson.getGeo().getCountry().getId());
	}
}
