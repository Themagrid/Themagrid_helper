package nl.themagrid.helper.config;

import io.quarkus.arc.config.ConfigProperties;

@ConfigProperties(prefix = "jhipster")
public class JHipsterProperties {
    public Oidc oidc;

    public static class Oidc {
        public String logoutUrl;
    }
}
