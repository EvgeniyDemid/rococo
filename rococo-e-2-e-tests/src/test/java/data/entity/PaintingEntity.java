package data.entity;

import lombok.*;
import model.PaintingJson;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaintingEntity implements Serializable {

	private UUID id;
	private String title;
	private String description;
	private String content;
	private UUID museumId;
	private UUID artistId;
	public PaintingEntity fromJson(PaintingJson paintingJson){
		return new PaintingEntity(
		paintingJson.getId(),
		paintingJson.getTitle(),
		paintingJson.getDescription(),
		paintingJson.getContent(),
		paintingJson.getMuseum().getId(),
		paintingJson.getArtist().getId()
		);
	}
}
