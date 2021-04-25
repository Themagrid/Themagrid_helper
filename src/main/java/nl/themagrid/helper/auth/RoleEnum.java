package nl.themagrid.helper.auth;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public enum RoleEnum {
    PCM_ADMIN,
    PCM_COMMISSIE,
    PCM_CONSULTANT,
    PCM_USER
}
