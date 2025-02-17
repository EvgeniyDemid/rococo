package jupiter.extension;

import data.entity.ArtistEntity;
import data.repository.ArtistRepositorySpringJdbc;
import data.repository.PaintingRepositorySpringJdbc;
import jupiter.annotation.TestArtist;
import model.ArtistJson;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

import java.util.UUID;


public class ArtistExtension implements BeforeEachCallback, ParameterResolver, AfterEachCallback {

	private final ArtistRepositorySpringJdbc artistRepositorySpringJdbc = new ArtistRepositorySpringJdbc();
	public final PaintingRepositorySpringJdbc paintingJdbc = new PaintingRepositorySpringJdbc();
	public static final ExtensionContext.Namespace NAMESPACE
			= ExtensionContext.Namespace.create(ArtistExtension.class);

	@Override
	public void beforeEach(ExtensionContext context) {
		AnnotationSupport.
				findAnnotation(
						context.getRequiredTestMethod(),
						TestArtist.class
				).ifPresent(testArtist -> {
					ArtistJson artist = new ArtistJson().fromArtistEntity(artistRepositorySpringJdbc.createArtist(
							new ArtistEntity().fromJson(new ArtistJson().random())
					));
					context.getStore(NAMESPACE).put(context.getUniqueId(), artist);
				});

	}

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {

		return parameterContext.getParameter().getType().isAssignableFrom(ArtistJson.class);
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {

		return extensionContext.getStore(NAMESPACE).get(extensionContext.getUniqueId(), ArtistJson.class);
	}

	@Override
	public void afterEach(ExtensionContext context)  {
		AnnotationSupport.
				findAnnotation(
						context.getRequiredTestMethod(),
						TestArtist.class
				).ifPresent(testArtist -> {
					UUID artistUUID = context.getStore(NAMESPACE).
							get(context.getUniqueId(), ArtistJson.class).getId();
					artistRepositorySpringJdbc.deleteArtist(artistUUID);
					paintingJdbc.deletePaintingByArtistId(artistUUID);
				}	);
	}
}
