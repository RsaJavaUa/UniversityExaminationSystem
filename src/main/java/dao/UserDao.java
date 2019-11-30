package dao;

import dao.interfaces.AbstractDao;
import dao.interfaces.ResultSetMapper;
import dao.interfaces.StatementMapper;
import entities.User;
import enums.Mark;
import enums.Role;
import org.apache.log4j.Logger;
import util.SpecialityMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;

import static util.RoleMapper.getRoleByString;

public class UserDao extends AbstractDao<User> {
    private static final Logger LOGGER = Logger.getLogger(UserDao.class);

    private static final String SELECT_STUDENT_BY_ID = "SELECT * FROM student as s inner join speciality as sp" +
            " on s.speciality_id = sp.id WHERE s.id=?";
    private static final String SELECT_SPECIALITY_EXAMS_BY_STUDENT_ID = "select sp.speciality_name, e.exam_name," +
            " rating.mark from student as s" +
            "                             inner join exam_student_rating as rating\n" +
            "                                        on s.id = rating.student_id\n" +
            "                             inner join exam as e\n" +
            "                                        on rating.id = e.id\n" +
            "                             inner join speciality as sp\n" +
            "                                        on e.speciality_id = sp.id\n" +
            "where s.id = ?";

    private static final String SELECT_ALL_STUDENTS = "SELECT * FROM student as s" +
            " inner join speciality as sp on s.speciality_id = sp.id";
    private static final String SELECT_EXAMS_FOR_STUDENT = "select e.exam_name, rating.mark  from student as s\n" +
            "                             inner join exam_student_rating as rating\n" +
            "                                        on s.id = rating.student_id\n" +
            "                             inner join exam as e\n" +
            "                                        on rating.id = e.id\n" +
            "                             inner join speciality as sp\n" +
            "                                        on e.speciality_id = sp.id order by s.id WHERE s.id=?";

    private static final String SELECT_BY_FIELDS = "SELECT * FROM student as s " +
            "inner join speciality as sp on s.speciality_id = sp.id" +
            " WHERE email =?";

    private static final String SQL_INSERT = "INSERT INTO student (first_name, last_name, speciality_id, email, " +
            "stud_password, role) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE student " +
            "SET  first_name =?, last_name =?, speciality_id =?, email =?, stud_password =?, role =?" +
            " WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM student WHERE id=?";
    private static final String SELECT_BY_LOGIN_PASSWORD = "SELECT * FROM student WHERE (email = ? AND stud_password= ?)";

    private SpecialityMapper specialityMapper = new SpecialityMapper();

    private ResultSetMapper<User> mapToStudent = rs -> new User().setId(rs.getLong("id"))
            .setLastName(rs.getString("last_name"))
            .setFirstName(rs.getString("first_name"))
            .setEmail(rs.getString("email"))
            .setPassword(rs.getString("stud_password"))
            .setSpecialityName(specialityMapper.fromIdToNameSpeciality(rs.getLong("speciality_id")))
            .setRole(getRoleByString(rs.getString("role")));

    private Function<ResultSet, Map<String, Mark>> mapExamMarks = rs -> {
        Map<String, Mark> result = new HashMap<>();
        try {
            while (rs.next()) {
                result.put(rs.getString("exam_name"), Mark.valueOf(rs.getString("mark").toUpperCase()));
            }
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
        return result;
    };

    private Map<String, Mark> getMarksAndExams(Long id) throws SQLException {
        PreparedStatement preparedStatement =
                getPreparedStatement(SELECT_SPECIALITY_EXAMS_BY_STUDENT_ID);
        if (preparedStatement != null) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapExamMarks.apply(resultSet);
        } else return null;
    }

    @Override
    public List<User> getAll() {
        List<User> all = getAll(SELECT_ALL_STUDENTS, mapToStudent);
        all.forEach(student -> {
            try {
                student.setExamNamesMarks(getMarksAndExams(student.getId()));
            } catch (SQLException e) {
                LOGGER.info(e.getMessage());
            }
        });
        return all;
    }

    @Override
    public boolean deleteEntity(User entity) {
        return createUpdate(DELETE_BY_ID, preparedStatement
                -> preparedStatement.setLong(1, removeOtherEntitiesLinks(entity).getId()));
    }

    public void deleteEntityByEmail(User user) {
        getStudentsByEmail(user)
                .forEach(element -> createUpdate(DELETE_BY_ID, ps -> ps.setLong(1, element.getId())));
    }

    @Override
    public boolean saveEntity(User entity) {
        return createUpdate(SQL_INSERT, ps -> {
            setPreparedStatementStudent(ps, entity);
        });
    }

    @Override
    public boolean updateEntity(User entity) {
        return createUpdate(UPDATE, ps -> {
            ps.setLong(7, entity.getId());
            setPreparedStatementStudent(ps, entity);
        });
    }

    @Override
    public Optional<User> getEntityById(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement prepStatement = getPreparedStatement(SELECT_STUDENT_BY_ID)) {
            prepStatement.setLong(1, id);
            ResultSet resultSet = prepStatement.executeQuery();
            resultSet.next();
            User user = mapToStudent.map(resultSet);
            user.setExamNamesMarks(getMarksAndExams(user.getId()));
            return Optional.of(user);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return Optional.empty();
        }
    }

    private void setPreparedStatementStudent(PreparedStatement ps, User entity) {
        try {
            ps.setString(1, Optional.of(entity.getFirstName()).orElse(null));
            ps.setString(2, Optional.of(entity.getLastName()).orElse(null));
            ps.setLong(3, specialityMapper.getSpecialityIdByName(entity.getSpecialityName()));
            ps.setString(4, entity.getEmail());
            ps.setString(5, entity.getPassword());
            ps.setString(6, Role.STUDENT.name());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public User getByEmailPassword(String email, String password) {
        StatementMapper<User> getStudentsByEmailPassword = ps -> {
            ps.setString(1, email);
            ps.setString(2, password);
        };

        List<User> all = getAll(SELECT_BY_LOGIN_PASSWORD, getStudentsByEmailPassword, mapToStudent);
        return all.size() > 0 ? all.get(0) : null;
    }


    private List<User> getStudentsByEmail(User user) {
        StatementMapper<User> getStudentsByFieldsMapper = ps -> {
            ps.setString(1, user.getEmail());
        };
        List<User> all = getAll(SELECT_BY_FIELDS, getStudentsByFieldsMapper, mapToStudent);
        return all.size() > 0 ? all : Collections.emptyList();
    }

    private User removeOtherEntitiesLinks(User user) {
        user.setSpecialityName(null);
        return user;
    }
}
