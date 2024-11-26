package ui.page;

import ui.form.ArtistForm;
import ui.form.NewArtistForm;

public class ArtistPage {
	public ArtistForm onArtistPage() {
		return new ArtistForm();
	}

	public NewArtistForm onNewArtistForm() {
		return new NewArtistForm();
	}
}
