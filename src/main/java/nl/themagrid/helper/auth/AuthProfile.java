package nl.themagrid.helper.auth;

import io.quarkus.oidc.runtime.OidcJwtCallerPrincipal;
import io.quarkus.security.ForbiddenException;
import io.quarkus.security.identity.SecurityIdentity;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonValue;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class AuthProfile {
    @Inject
    SecurityIdentity securityIdentity;
    private String id;
    private String name;
    private List<String> roles;
    private List<String> groups;
    private String token;
    private RoleEnum pcmRole;

    public RoleEnum getPcmRole() {
        return pcmRole;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public List<String> getGroups() {
        return groups;
    }

    @PostConstruct
    private void postConstruct() {
        this.id = ((OidcJwtCallerPrincipal) securityIdentity.getPrincipal()).getClaim("sub");
        this.name = securityIdentity.getPrincipal().getName();
        this.roles = new ArrayList<>(securityIdentity.getRoles());
        this.groups = getGroupsFromPrincipal();
        this.token = "Bearer " + ((OidcJwtCallerPrincipal)this.securityIdentity.getPrincipal()).getRawToken();
        this.pcmRole = setPcmRole();
    }


    public boolean isAdmin() {
        return roles.contains(RoleEnum.PCM_ADMIN.name());
    }

    public boolean isCommissie() {
        return roles.contains(RoleEnum.PCM_COMMISSIE.name());
    }

    public boolean isConsultant() {
        return roles.contains(RoleEnum.PCM_CONSULTANT.name());
    }

    public boolean isUser() {
        return roles.contains(RoleEnum.PCM_USER.name());
    }

    List<String> getGroupsFromPrincipal() {
        JsonArray json = ((OidcJwtCallerPrincipal) securityIdentity.getPrincipal()).getClaim("user_groups");
        return json.stream().map(JsonValue::toString).map(str -> str.replaceAll("\"", "")).collect(Collectors.toList());
    }

    RoleEnum setPcmRole(){
        if(isUser()){
            return RoleEnum.PCM_USER;
        }else if(isConsultant()){
            return RoleEnum.PCM_CONSULTANT;
        }else if(isCommissie()){
            return RoleEnum.PCM_COMMISSIE;
        }else if(isAdmin()){
            return RoleEnum.PCM_ADMIN;
        }else{
            throw new ForbiddenException("User has no PCM role");
        }
    }

}
