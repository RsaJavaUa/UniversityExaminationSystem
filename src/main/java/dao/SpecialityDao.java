package dao;

import dao.interfaces.ResultSetMapper;
import entities.Speciality;
import org.apache.log4j.Logger;
import persistance.ConnectionFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpecialityDao extends AbstractDao<Speciality> {

    private static final Logger LOGGER = Logger.getLogger(SpecialityDao.class);

    private static final String SELECT_SPECIALITIES_WITHOUT_EXAMS = "SELECT * FROM speciality";
    private static final String SELECT_SPECIALITIES_NAMES = "SELECT speciality_name FROM speciality";

    private ResultSetMapper<Speciality> mapToSpeciality = rs -> new Speciality().setId(rs.getLong(1))
            .setName(rs.getString(2));


    @Override
    public List<Speciality> getAll() {
        return getAll(SELECT_SPECIALITIES_WITHOUT_EXAMS, mapToSpeciality);
    }

    public List<String> getAllNames() {
        try (Statement statement = ConnectionFactory.getConnection().createStatement()) {
            return  createNamesList(statement.executeQuery(SELECT_SPECIALITIES_NAMES));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    private List<String> createNamesList(ResultSet resultSet) throws SQLException {
        List<String> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(resultSet.getString(1));
        }
        return result;
    }

    @Override
    public boolean deleteEntity(Speciality entity) {
        return false;
    }

    @Override
    public boolean saveEntity(Speciality entity) {
        return false;
    }

    @Override
    public boolean updateEntity(Speciality entity) {
        return false;
    }

    @Override
    public Optional<Speciality> getEntityById(Long id) {
        return Optional.empty();
    }
}
