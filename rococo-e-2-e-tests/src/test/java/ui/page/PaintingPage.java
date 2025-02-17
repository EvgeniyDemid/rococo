package ui.page;

import ui.form.PaintingAddForm;
import ui.form.PaintingEditForm;
import ui.form.PaintingForm;
import ui.form.PaintingProfileForm;

public class PaintingPage {
	public PaintingAddForm onPaintingAddForm() {
		return new PaintingAddForm();
	}

	public PaintingForm onPaintingForm() {
		return new PaintingForm();
	}

	public PaintingEditForm onPaintingEditForm(){
	return new PaintingEditForm();
	}
	public PaintingProfileForm onPaintingProfileForm(){
	return new PaintingProfileForm();
	}
}
