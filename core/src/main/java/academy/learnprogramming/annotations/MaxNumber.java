package academy.learnprogramming.annotations;

/*
    Creating Custom Qualifier
    Qualifier is an annotation that you apply to a bean.
    Create a qualifier annotation and use it with bean definition method ,
    then the container will know what needs to be autowired and we don't have to depend
    specifically on a bean name
 */

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
    @Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
    This indicates the contexts in which an annotation type is applicable
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface MaxNumber {
}
