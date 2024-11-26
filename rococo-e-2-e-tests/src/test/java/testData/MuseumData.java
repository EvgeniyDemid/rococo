package testData;

import com.github.javafaker.Faker;
import model.MuseumJson;

public class MuseumData {
	public static MuseumJson[] museumData = new MuseumJson[]{
			MuseumJson.
					builder().
					id(null).
					title(new Faker().name().name()).
					description("Са́нта-Мари́я-де́лле-Гра́цие — главная церковь доминиканского монастыря в западной части Милана.").
					photo("C:\\Users\\Yevgeny.Demidov\\IdeaProjects\\rococo\\rococo-e-2-e-tests\\src\\test\\java\\testData\\painting\\Museum\\SantaMariadelleGrazia.png").
					geo(GeoData.geoData[0]).
					build(),
			MuseumJson.
					builder().
					id(null).
					title(new Faker().name().name()).
					description("Эрмита́ж, Госуда́рственный Эрмита́ж — российский государственный художественный и культурно-исторический музей в Санкт-Петербурге, одно из крупнейших в мире учреждений подобного рода.").
					photo("rococo-e-2-e-tests/src/test/java/testData/painting/Museum/Hermitage.png").
					geo(GeoData.geoData[1]).
					build(),
			MuseumJson.
					builder().
					id(null).
					title(new Faker().name().name()).
					description("Музей Лувра — один из крупнейших и самый популярный художественный музей мира. Музей расположен в центре Парижа, на правом берегу Сены, на улице Риволи, в 1-м округе столицы. В 2018 году число посетителей Лувра превысило 10 млн человек, что является рекордом. Здание музея — старинный королевский дворец").
					photo("rococo-e-2-e-tests/src/test/java/testData/painting/Museum/Luvr.png").
					geo(GeoData.geoData[1]).
					build()
	};
}
