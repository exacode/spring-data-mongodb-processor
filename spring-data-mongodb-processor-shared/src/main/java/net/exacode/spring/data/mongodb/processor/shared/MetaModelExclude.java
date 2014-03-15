package net.exacode.spring.data.mongodb.processor.shared;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Identifies a field in a domain object that should not be included in it's
 * meta model.
 * 
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MetaModelExclude {

	boolean pathOnly() default false;

}
