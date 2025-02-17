package ui.page;

import ui.form.*;

public class ArtistPage {
	public ArtistForm onArtistPage() {
		return new ArtistForm();
	}

	public NewArtistForm onNewArtistForm() {
		return new NewArtistForm();
	}

	public ArtistProfileForm onArtistProfileForm() {
		return new ArtistProfileForm();
	}

	public ArtistEditForm onArtistEditForm() {
		return new ArtistEditForm();
	}

	public ArtistAddPaintingForm onArtistAddPaintingForm() {
		return new ArtistAddPaintingForm();
	}
}
