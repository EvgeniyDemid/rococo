package data.repository;

import data.entity.MuseumEntity;

import java.util.UUID;

public interface MuseumRepository {
	MuseumEntity createMuseum (MuseumEntity museumEntity);
	MuseumEntity updateMuseum (MuseumEntity museumEntity);
	MuseumEntity findByIdMuseum (UUID id);
	void deleteMuseum (UUID id);
	void deleteMuseumByName(String name);
}
