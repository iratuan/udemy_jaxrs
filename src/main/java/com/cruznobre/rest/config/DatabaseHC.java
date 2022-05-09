package com.cruznobre.rest.config;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.SneakyThrows;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.sql.DataSource;
import java.sql.SQLException;

@Readiness
@ApplicationScoped
public class DatabaseHC implements HealthCheck {

    @Resource(lookup = "jdbc/datasource")
    private DataSource dataSource;

    @SneakyThrows
    @Override
    public HealthCheckResponse call() {
        try {
            dataSource.getConnection();
            return HealthCheckResponse.up("databaseReady");
        } catch (SQLException e) {
            return HealthCheckResponse.down("databaseReady");
        }

    }

}
