package nl.themagrid.helper.interceptor;

import io.quarkus.security.ForbiddenException;
import nl.themagrid.helper.auth.AuthProfile;
import nl.themagrid.helper.auth.RoleEnum;
import nl.themagrid.helper.interceptor.annotation.MinimumRole;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@MinimumRole
@Interceptor
@Priority(0)
public class MinimumRoleInterceptor {

    @Inject
    AuthProfile authProfile;

    @AroundInvoke
    private Object intercept(InvocationContext ic) throws Exception {
       RoleEnum minimum = ic.getMethod().getAnnotation(MinimumRole.class).role();

       if(minimum.compareTo(authProfile.getPcmRole()) < 0 ){
           throw new ForbiddenException("Rol te laag. Rol: " + RoleEnum.PCM_USER.name() + " Minimum rol:" + minimum.name());
       }
        return ic.proceed();
    }
}

