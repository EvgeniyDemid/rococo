package data.repository;

import data.entity.PaintingEntity;

import java.util.UUID;

public interface PaintingRepository {
	PaintingEntity createPainting(PaintingEntity paintingEntity);
	PaintingEntity updatePainting(PaintingEntity paintingEntity);
	PaintingEntity findByIdPainting(UUID id);
	void deletePainting(UUID id);
}
