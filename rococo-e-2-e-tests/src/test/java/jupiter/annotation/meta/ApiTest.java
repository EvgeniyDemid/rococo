package jupiter.annotation.meta;

import io.qameta.allure.junit5.AllureJunit5;
import jupiter.extension.ApiLoginExtension;
import jupiter.extension.PaintingExtension;
import jupiter.extension.UserExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ExtendWith({UserExtension.class, AllureJunit5.class, ApiLoginExtension.class, PaintingExtension.class})
public @interface ApiTest {
}
