package ui.page;

import ui.form.MuseumAddFrom;
import ui.form.MuseumEditFrom;
import ui.form.MuseumForm;
import ui.form.MuseumProfileFrom;

public class MuseumPage {
	public MuseumProfileFrom onMuseumProfileFrom() {
		return new MuseumProfileFrom();
	}

	public MuseumForm onMuseumForm() {
		return new MuseumForm();
	}

	public MuseumEditFrom onMuseumEditFrom() {
		return new MuseumEditFrom();
	}

	public MuseumAddFrom onMuseumAddFrom(){
	return new 	MuseumAddFrom();
	}
}
