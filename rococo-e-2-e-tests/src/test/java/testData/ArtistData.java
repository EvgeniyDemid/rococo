package testData;

import com.github.javafaker.Faker;
import model.ArtistJson;

public class ArtistData {

	public static ArtistJson[] artistData = new ArtistJson[]{
			ArtistJson.
					builder().
					id(null).
					name(new Faker().artist().name()).
					biography("Леонардо да Винчи родился 15 апреля 1452 года в селении Анкиано вблизи города Винчи (отсюда и произошла приставка к его фамилии). Отец и мать мальчика не были женаты, поэтому первые годы Леонардо провел с матерью. Вскоре отец, служивший нотариусом, забрал его к себе в семью").
					photo("C:\\Users\\Yevgeny.Demidov\\IdeaProjects\\rococo\\rococo-e-2-e-tests\\src\\test\\java\\testData\\painting\\Artist\\LeonardodaVinci.png").
					build(),
			ArtistJson.
					builder().
					id(null).
					name(new Faker().artist().name()).
					biography("Дата и место рождения: 25 января 1832г., Елабуга ,Дата и место смерти: 20 марта 1898г., Санкт-Петербург").
					photo("C:\\Users\\Yevgeny.Demidov\\IdeaProjects\\rococo\\rococo-e-2-e-tests\\src\\test\\java\\testData\\painting\\Artist\\Shishkin.png").
					build(),
			ArtistJson.
					builder().
					id(null).
					name(new Faker().artist().name()).
					biography("Дата и место рождения: 23 февраля 1879г., Киев, Украина Дата и место смерти: 15 мая 1935г., Санкт-Петербург").
					photo("rococo-e-2-e-tests/src/test/java/testData/painting/Artist/Malevich.png").
					build(),
			ArtistJson.
					builder().
					id(null).
					name(new Faker().artist().name()).
					biography("Дата и место рождения: 25 октября 1881г., Малага, Испания Дата и место смерти: 8 апреля 1973г., Мужен, Франция").
					photo("C:\\Users\\Yevgeny.Demidov\\IdeaProjects\\rococo\\rococo-e-2-e-tests\\src\\test\\java\\testData\\painting\\Artist\\Malevich.png").
					build(),
			ArtistJson.
					builder().
					id(null).
					name(new Faker().artist().name()).
					biography("Дата и место рождения: 11 мая 1904г., Фигерас, Испания Дата и место смерти: 23 января 1989г., Испания").
					photo("C:\\Users\\Yevgeny.Demidov\\IdeaProjects\\rococo\\rococo-e-2-e-tests\\src\\test\\java\\testData\\painting\\Artist\\Dali.png").
					build()
	};
}
