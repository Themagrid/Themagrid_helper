package nl.themagrid.helper.interceptor.annotation;


import nl.themagrid.helper.auth.RoleEnum;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@InterceptorBinding
@Target({METHOD, TYPE})
@Retention(RUNTIME)
public @interface MinimumRole {
    @Nonbinding
    RoleEnum role() default RoleEnum.PCM_USER;
}
