package testData;

public class RandomData {

	int number = (int) ((Math.random() * (10000) + 10000));

	public String randomArtist() {
		return "Художник" + number;
	}

	public String randomMuseum() {
		return "Музей " + number;
	}

	public String randomPainting() {
		return "Картина " + number;
	}

	public String randomBiography() {
		return "Biography" + number;
	}

	public String randomCity() {
		return "City" + number;
	}

	public String randomCountry() {
		return "Country" + number;
	}

	public String randomDescription() {
		return "Description" + number;
	}

	public String photoArtist() {
		return "C:\\Users\\Yevgeny.Demidov\\IdeaProjects\\rococo\\rococo-e-2-e-tests\\src\\test\\java\\testData\\painting\\Artist\\Dali.png";
	}

	public String photoMuseum() {
		return "C:\\Users\\Yevgeny.Demidov\\IdeaProjects\\rococo\\rococo-e-2-e-tests\\src\\test\\java\\testData\\painting\\Museum\\Hermitage.png";
	}

	public String photoPaint() {
		return "C:\\Users\\Yevgeny.Demidov\\IdeaProjects\\rococo\\rococo-e-2-e-tests\\src\\test\\java\\testData\\painting\\paint\\lastSupper.png";
	}
}
