package nl.themagrid.helper.config;

import io.quarkus.arc.config.ConfigProperties;

@ConfigProperties(prefix = "settings.db")
public class ThemagridDatabaseConfig {
    public String user = "admin";
    public String password = "secret";
    public String url = "localhost/postgres";
}
