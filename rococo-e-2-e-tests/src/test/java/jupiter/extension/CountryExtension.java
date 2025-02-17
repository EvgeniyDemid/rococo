package jupiter.extension;

import data.entity.CountryEntity;
import data.repository.CountryRepositorySpringJdbc;
import jupiter.annotation.TestCountry;
import model.CountryJson;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

public class CountryExtension implements BeforeEachCallback, ParameterResolver, AfterEachCallback {
	private final CountryRepositorySpringJdbc countryJdbc = new CountryRepositorySpringJdbc();
	private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(CountryExtension.class);

	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		AnnotationSupport.findAnnotation(
						context.getRequiredTestMethod(), TestCountry.class).
				ifPresent(testCountry -> {
					CountryJson countryJson = new CountryJson().random();
					countryJson.setId(countryJdbc.createCountry(new CountryEntity().fromJson(countryJson)).getId());
					context.getStore(NAMESPACE).put(context.getUniqueId(), countryJson);
				});
	}

	@Override
	public void afterEach(ExtensionContext context) throws Exception {
	}


	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
		return parameterContext.getParameter().getType().isAssignableFrom(CountryJson.class);
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
		return extensionContext.getStore(NAMESPACE).get(extensionContext.getUniqueId(), CountryJson.class);
	}
}
