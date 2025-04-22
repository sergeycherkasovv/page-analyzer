package hexlet.code.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.repository.BaseRepository;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.SQLException;

@Slf4j
public class DataBaseService {
    private static final String JDBC_H2_URL = "jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;";

    public static HikariDataSource connectedDataBase() throws IOException, SQLException {
        log.info("Using database URL: {}", getDatabaseUrl());
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(getDatabaseUrl());


        var dataSource = new HikariDataSource(hikariConfig);
        var sql = ReadFiles.readResourceFile("schema.sql");

        log.info("Executing SQL schema: \n{}", sql);
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement())    {
            statement.execute(sql);
        }
        BaseRepository.dataSource = dataSource;

        return dataSource;
    }

    private static String getDatabaseUrl() {
        return System.getenv().getOrDefault("JDBC_DATABASE_URL", JDBC_H2_URL);
    }
 }
