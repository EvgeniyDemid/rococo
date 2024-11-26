package data.repository;

import data.entity.ArtistEntity;

import java.util.UUID;

public interface ArtistRepository {

	ArtistEntity createArtist (ArtistEntity artistEntity);

	ArtistEntity updateArtist (ArtistEntity artistEntity);

	ArtistEntity findArtistById (UUID id);

	void deleteArtist (UUID id);
}
