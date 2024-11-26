package testData;

import com.github.javafaker.Faker;
import model.PaintingJson;

public class PaintingData {
	public static PaintingJson[] paintingData = new PaintingJson[]{
			PaintingJson.
					builder().
					id(null).
					title(new Faker().name().name()).
					description("«Та́йная ве́черя» — монументальная роспись работы Леонардо да Винчи, изображающая сцену последней трапезы Христа со своими учениками. Создана в 1495—1498 годы в доминиканском монастыре Санта-Мария-делле-Грацие в Милане").
					content("C:\\Users\\Yevgeny.Demidov\\IdeaProjects\\rococo\\rococo-e-2-e-tests\\src\\test\\java\\testData\\painting\\paint\\lastSupper.png").
					museum(MuseumData.museumData[0]).
					artist(ArtistData.artistData[0]).
					build(),
			PaintingJson.
					builder().
					id(null).
					title(new Faker().name().name()).
					description("«Спаситель мира» — картина, полностью либо частично атрибутируемая как произведение итальянского художника и учёного Высокого Возрождения Леонардо да Винчи, различными исследователями датируемая в пределах 1499—1510 годов").
					content("rococo-e-2-e-tests/src/test/java/testData/painting/paint/SaviorWorld.png").
					museum(MuseumData.museumData[1]).
					artist(ArtistData.artistData[0]).
					build(),
			PaintingJson.
					builder().
					id(null).
					title(new Faker().name().name()).
					description("«Утро в сосновом лесу» — картина русских живописцев Ивана Шишкина и Константина Савицкого, написанная в 1889 году; одно из наиболее популярных полотен Шишкина, хрестоматийный шедевр реалистической школы второй половины XIX века, один из самых известных пейзажей в истории русского искусства.").
					content("rococo-e-2-e-tests/src/test/java/testData/painting/paint/MorningPineForest.png").
					museum(MuseumData.museumData[1]).
					artist(ArtistData.artistData[1]).
					build(),
			PaintingJson.
					builder().
					id(null).
					title(new Faker().name().name()).
					description(" И когда летом 1872 года на выставке, которую организовало петербургское Общество поощрения художников, была представлена его работа «Сосновый бор. Мачтовый лес в Вятской губернии», она произвела огромное впечатление не только на зрителей, но и на критиков, и на коллег по искусству.").
					content("rococo-e-2-e-tests/src/test/java/testData/painting/paint/MastForestVyatkaProvince.png").
					museum(MuseumData.museumData[1]).
					artist(ArtistData.artistData[1]).
					build(),
			PaintingJson.
					builder().
					id(null).
					title(new Faker().name().name()).
					description("Чёрный супрематический квадрат — картина Казимира Малевича, созданная в 1915 году. Это одна из самых известных картин в мировом искусстве").
					content("rococo-e-2-e-tests/src/test/java/testData/painting/paint/Black square.png").
					museum(MuseumData.museumData[1]).
					artist(ArtistData.artistData[2]).
					build(),
			PaintingJson.
					builder().
					id(null).
					title(new Faker().name().name()).
					description("Основная часть работ будет экспонироваться в Большом Дворце. 120 полотен живописца будут соседствовать с картинами Веласкеса, Гойи, Рембрандта и Ван Гога. В Лувре можно будет увидеть его вариации на тему «Алжирских женщин» Делакруа. А в Музее д’Орсэ решили разместить бок о бок оригинал «Завтрака на траве» кисти Мане и более позднюю версию этого сюжета, выполненную Пикассо.").
					content("rococo-e-2-e-tests/src/test/java/testData/painting/paint/BreakfasGrass.png").
					museum(MuseumData.museumData[2]).
					artist(ArtistData.artistData[3]).
					build(),
			PaintingJson.
					builder().
					id(null).
					title(new Faker().name().name()).
					description("Дали был искусным рисовальщиком, известен яркий и причудливый изображения в его сюрреалистических работ.").
					content("rococo-e-2-e-tests/src/test/java/testData/painting/paint/ExquisiteCorpse.png").
					museum(MuseumData.museumData[1]).
					artist(ArtistData.artistData[4]).
					build()
	};
};
