package jupiter.annotation;

import enums.UserType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static enums.UserType.REGISTERED;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestUser {
	UserType USER_TYPE() default REGISTERED;

	String username() default "";

	String password() default "";
}
