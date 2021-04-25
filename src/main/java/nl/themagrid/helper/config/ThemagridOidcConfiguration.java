package nl.themagrid.helper.config;

import io.quarkus.arc.config.ConfigProperties;

@ConfigProperties(prefix = "settings.oidc")
public class ThemagridOidcConfiguration {
        public String url;
        public String realm;
        public String client;
        public String secret;
}
