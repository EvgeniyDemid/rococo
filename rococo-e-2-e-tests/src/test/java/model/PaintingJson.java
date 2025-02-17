package model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class PaintingJson {

	@JsonProperty("id")
	UUID id;
	@JsonProperty("title")
	String title;
	@JsonProperty("description")
	String description;
	@JsonProperty("content")
	String content;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("museum")
	MuseumJson museum;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("artist")
	ArtistJson artist;

	public PaintingJson random() {
		RandomData randomData = new RandomData();
		return new PaintingJson(
				null,
				randomData.randomPainting(),
				randomData.randomDescription(),
				randomData.photoPaint(),
				new MuseumJson().random(),
				new ArtistJson().random()
		);
	}
}
