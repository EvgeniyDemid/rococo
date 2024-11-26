package jupiter.extension;

import data.entity.ArtistEntity;
import data.entity.CountryEntity;
import data.entity.MuseumEntity;
import data.entity.PaintingEntity;
import data.repository.ArtistRepositorySpringJdbc;
import data.repository.CountryRepositorySpringJdbc;
import data.repository.MuseumRepositorySpringJdbc;
import data.repository.PaintingRepositorySpringJdbc;
import jupiter.annotation.TestPainting;
import model.PaintingJson;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;
import testData.PaintingData;

public class PaintingExtension implements BeforeEachCallback, ParameterResolver, AfterEachCallback {
	private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(PaintingExtension.class);
	private final ArtistRepositorySpringJdbc artistJdbc = new ArtistRepositorySpringJdbc();
	private final MuseumRepositorySpringJdbc musJdbc = new MuseumRepositorySpringJdbc();
	private final PaintingRepositorySpringJdbc painJdbc = new PaintingRepositorySpringJdbc();
	private final CountryRepositorySpringJdbc countryJdbc = new CountryRepositorySpringJdbc();

	@Override
	public void beforeEach(ExtensionContext context) {
		AnnotationSupport.findAnnotation(
				context.getRequiredTestMethod(),
				TestPainting.class).ifPresent(testPainting -> {

			PaintingJson painting = PaintingData.paintingData[0];

			painting.getMuseum().getGeo().getCountry().setId(
					countryJdbc.createCountry(
							new CountryEntity().fromJson(
									painting.getMuseum().getGeo().getCountry()
							)).getId()
			);

			painting.getMuseum().setId(
					musJdbc.createMuseum(
							new MuseumEntity().fromJson(
									painting.getMuseum())).getId()
			);
			painting.getArtist().setId(
					artistJdbc.createArtist(
							new ArtistEntity().fromJson(
									painting.getArtist())).getId()
			);
			painting.setId(
					painJdbc.createPainting(
							new PaintingEntity().fromJson(
									painting)).getId());

			context.getStore(NAMESPACE).put(context.getUniqueId(), painting);
		});

	}

	@Override
	public void afterEach(ExtensionContext context) throws Exception {
		countryJdbc.deleteCountry(
				context.getStore(NAMESPACE).
						get(context.getUniqueId(),PaintingJson.class).
						getMuseum().
						getGeo().
						getCountry().
						getId())
		;
	}

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
		return parameterContext.getParameter().getType().isAssignableFrom(PaintingJson.class);
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
		return extensionContext.getStore(NAMESPACE).get(extensionContext.getUniqueId(), PaintingJson.class);
	}
}
