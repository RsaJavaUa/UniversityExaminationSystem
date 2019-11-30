package dao.interfaces;

import org.apache.log4j.Logger;
import persistance.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class AbstractDao<T> implements DaoInterface<T> {

    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class);


    public List<T> getAll(String query, ResultSetMapper<T> resultSetMapper) {

        List<T> result = new ArrayList<>();

        try (PreparedStatement preparedStatement = getPreparedStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            result = setIntoList(resultSet, resultSetMapper);
        } catch (SQLException e) {
            LOGGER.error("Exception while getting all entities");
        }
        return result;
    }

    private List<T> setIntoList(ResultSet resultSet, ResultSetMapper<T> resultSetMapper) throws SQLException {
        List<T> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(resultSetMapper.map(resultSet));
        }
        return result;
    }

    public boolean createUpdate(String query, StatementMapper<T> statementMapper) {
        try (PreparedStatement preparedStatement = getPreparedStatement(query)) {
            statementMapper.map(preparedStatement);

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.error("Error while creating or updating entity" + e.getMessage());
        }
        return false;
    }

    protected List<T> getAll(String query, StatementMapper<T> statementMapper, ResultSetMapper<T> resultSetMapper) {
        List<T> result = Collections.emptyList();
        try (PreparedStatement preparedStatement = getPreparedStatement(query)) {
            statementMapper.map(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<T> ts = setIntoList(resultSet, resultSetMapper);
            return ts;
        } catch (SQLException e) {
            LOGGER.error("Exception while getting all entities " + e.getMessage());
        }
        return result;
    }

    public PreparedStatement getPreparedStatement(String query) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        return connection.prepareStatement(query);
    }

    public Connection getConnection() {

        Connection connection = ConnectionFactory.getConnection();
        return connection;

    }
}
