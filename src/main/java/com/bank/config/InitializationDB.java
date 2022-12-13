package com.bank.config;

import org.flywaydb.core.Flyway;
import org.jetbrains.annotations.NotNull;

public class InitializationDB {
    private final @NotNull DBInfo CREDS;

    public InitializationDB(@NotNull DBInfo CREDS) {
        this.CREDS = CREDS;
    }

    public void initDB() {
        final Flyway flyway = Flyway
                .configure()
                .dataSource(
                        CREDS.url(),
                        CREDS.login(),
                        CREDS.password()
                )
                .locations("db")
                .load();
        flyway.migrate();
    }
}
