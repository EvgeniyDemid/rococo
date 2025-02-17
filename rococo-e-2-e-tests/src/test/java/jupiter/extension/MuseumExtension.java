package jupiter.extension;

import data.entity.CountryEntity;
import data.entity.MuseumEntity;
import data.repository.CountryRepositorySpringJdbc;
import data.repository.MuseumRepositorySpringJdbc;
import data.repository.PaintingRepositorySpringJdbc;
import jupiter.annotation.TestMuseum;
import model.MuseumJson;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

public class MuseumExtension implements BeforeEachCallback, ParameterResolver, AfterEachCallback {

	private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(MuseumExtension.class);
	private final MuseumRepositorySpringJdbc museumJdbc = new MuseumRepositorySpringJdbc();
	private final CountryRepositorySpringJdbc countryJdbc = new CountryRepositorySpringJdbc();
	private final PaintingRepositorySpringJdbc paintingJdbc = new PaintingRepositorySpringJdbc();

	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		AnnotationSupport.findAnnotation(
				context.getRequiredTestMethod(), TestMuseum.class
		).ifPresent(testMuseum -> {
			MuseumJson museum = new MuseumJson().random();
			museum.getGeo().
					getCountry().
					setId(countryJdbc.createCountry(
							new CountryEntity().fromJson(
									museum.getGeo().getCountry())).getId()
					);

			if (testMuseum.crateInDb()) {
				museum.setId(museumJdbc.createMuseum(new MuseumEntity().fromJson(museum)).getId());
			}
			context.getStore(NAMESPACE).put(context.getUniqueId(), museum);
		});
	}

	@Override
	public void afterEach(ExtensionContext context) {
		AnnotationSupport.findAnnotation(
				context.getRequiredTestMethod(), TestMuseum.class
		).ifPresent(testMuseum -> {
			MuseumJson museumJson = context.getStore(NAMESPACE).get(context.getUniqueId(), MuseumJson.class);
			paintingJdbc.deletePaintingByMuseumId(museumJson.getId());
			museumJdbc.deleteMuseumByName(museumJson.getTitle());
			museumJdbc.deleteMuseum(museumJson.getId());
			countryJdbc.deleteCountry(museumJson.getGeo().getCountry().getId());
		});
	}


	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
		return parameterContext.getParameter().getType().isAssignableFrom(MuseumJson.class);
	}

	@Override
	public MuseumJson resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
		return extensionContext.getStore(NAMESPACE).get(extensionContext.getUniqueId(), MuseumJson.class);
	}
}
