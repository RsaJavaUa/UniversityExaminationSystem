package persistance;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private final static Logger LOGGER = Logger.getLogger(ConnectionFactory.class);

    private static HikariConfig config = new HikariConfig(); ;
    private static HikariDataSource hikariDataSource;

    public static DataSource setDataSource(String fileName) {
        Properties properties= createPropertiesByPath(fileName);
        config.setJdbcUrl(properties.getProperty("DB.URL"));
        config.setSchema(properties.getProperty("DB.NAME"));

        config.setUsername(properties.getProperty("DB.USERNAME"));
        config.setPassword(properties.getProperty("DB.PASSWORD"));
        config.addDataSourceProperty("cachePrepStmts", properties.getProperty("PrepStmts"));
        config.addDataSourceProperty("prepStmtCacheSize", properties.getProperty("CacheSize"));
        config.addDataSourceProperty("prepStmtCacheSqlLimit", properties.getProperty("Limit"));
        return new HikariDataSource(config);
    }

    static {
        Properties properties= createPropertiesByPath("db/db.properties");
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl(properties.getProperty("DB.URL"));
        config.setSchema(properties.getProperty("DB.NAME"));
        config.setUsername(properties.getProperty("DB.USERNAME"));
        config.setPassword(properties.getProperty("DB.PASSWORD"));
        config.addDataSourceProperty("cachePrepStmts", properties.getProperty("PrepStmts"));
        config.addDataSourceProperty("prepStmtCacheSize", properties.getProperty("CacheSize"));
        config.addDataSourceProperty("prepStmtCacheSqlLimit", properties.getProperty("Limit"));
        config.setConnectionTimeout(Long.parseLong(properties.getProperty("TIMEOUT")));
        hikariDataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = hikariDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }



    private static Properties createPropertiesByPath(String fileName) {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String dbPropertiesPath = rootPath + fileName;
        Properties result = new Properties();
        try {
            result.load(new FileInputStream(dbPropertiesPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
