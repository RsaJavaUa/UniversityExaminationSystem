package persistance;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.jdbc.MysqlDataSourceFactory;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private final static Logger LOGGER = Logger.getLogger(ConnectionFactory.class);

    private final static ConnectionFactory INSTANCE = new ConnectionFactory();
    private static DataSource dataSource;

    private ConnectionFactory() {
    }

    static {
        dataSource = setDataSource("/db/db.properties");
        LOGGER.info("Datasource is created");
    }

    public static DataSource setDataSource(String propertiesFilePath) {
        Properties properties = new Properties();
        try {
            properties.load(MysqlDataSourceFactory.class.getResourceAsStream(propertiesFilePath));
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setURL(properties.getProperty("DB_URL"));
            mysqlDataSource.setDatabaseName(properties.getProperty("DB_NAME"));
            mysqlDataSource.setCharacterEncoding(properties.getProperty("DB_CHARACTER_ENCODING"));
            mysqlDataSource.setUser(properties.getProperty("DB_USERNAME"));
            mysqlDataSource.setPassword(properties.getProperty("DB_PASSWORD"));
            dataSource = mysqlDataSource;
        } catch (IOException | SQLException e) {
            LOGGER.error(e.getMessage() + "Error while creating datasource");
        }
        return dataSource;
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Connection creation error" + e.getMessage());
        }
        return connection;
    }

    public static PreparedStatement getPreparedStatement(String query) throws SQLException {
        return getConnection().prepareStatement(query);
    }
}
