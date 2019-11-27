package dao;

import dao.interfaces.AbstractDao;
import dao.interfaces.ResultSetMapper;
import entities.Exam;
import entities.User;
import enums.Mark;
import org.apache.log4j.Logger;
import persistance.ConnectionFactory;
import util.SpecialityMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class ExamDao extends AbstractDao<Exam> {

    private static final Logger LOGGER = Logger.getLogger(ExamDao.class);

    private static final String SELECT_ALL = "SELECT * FROM exam";
    private static final String SELECT_EXAM_BY_ID = "SELECT * FROM exam AS e INNER JOIN exam_student_rating AS esr" +
            " ON e.id = esr.exam_id " +
            "INNER JOIN student as s ON esr.student_id = s.id" +
            "WHERE e.id=?";
    private static final String SELECT_MARKS_AND_STUD_BY_ID = "select s.id, s.last_name, s.first_name,  s.speciality_id, " +
            "e.exam_name, est.mark from exam_student_rating as est inner join exam as e on " +
            "est.exam_id = e.id " +
            "inner join student as s on est.student_id = s.id where e.id=?";
    private static final String INSERT_EXAM = "INSERT INTO exam(exam_name, date_of_exam, speciality_id VALUES (?, ?, ?)";
    private static final String INSERT_EXAM_MARK = "INSERT INTO exam_student_rating" +
            " (student_id, exam_id, mark) VALUES (?, ?, ?)";
    private static final String UPDATE_EXAM = "UPDATE exam " +
            "SET  exam_name =?, date_of_exam =?, speciality_id =? WHERE id=?";
    private static final String UPDATE_EXAM_MARK = "UPDATE exam_student_rating " +
            "SET  student_id =?, mark =? WHERE exam_id=?";
    private static final String DELETE_BY_ID = "DELETE FROM exam WHERE id=?";

    private SpecialityMapper specialityMapper = new SpecialityMapper();

    private Function<ResultSet, Map<User, Mark>> mapToStudentMark = rs -> {
        Map<User, Mark> result = new HashMap<>();
        try {
            while (rs.next()) {
                result.put(new User().setId(rs.getLong("s.id"))
                                .setFirstName(rs.getString("s.first_name"))
                                .setLastName(rs.getString("s.last_name"))
                                .setSpecialityName(
                                        specialityMapper.fromIdToNameSpeciality(rs.getLong("s.speciality_id")))
                        , Mark.valueOf(rs.getString("est.mark")));
            }
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
        return result;
    };

    private ResultSetMapper<Exam> toExam = resultSet -> new Exam()
            .setId(resultSet.getLong("id"))
            .setDateExam(resultSet.getLong("date_of_exam"))
            .setName(resultSet.getString("exam_name"))
            .setSpecialityName(specialityMapper.fromIdToNameSpeciality(resultSet.getLong("speciality_id")));

    @Override
    public List<Exam> getAll() {
        List<Exam> all = getAll(SELECT_ALL, toExam);
        all.forEach(exam -> exam.setStudentMarkMap(setStudentAndMarkByID(exam.getId())));
        return all;
    }

    private Map<User, Mark> setStudentAndMarkByID(Long id) {
        try {
            PreparedStatement preparedStatement = ConnectionFactory.getPreparedStatement(SELECT_MARKS_AND_STUD_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToStudentMark.apply(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteEntity(Exam entity) {
        return createUpdate(DELETE_BY_ID, ps -> ps.setLong(1, entity.getId()));
    }

    @Override
    public boolean saveEntity(Exam entity) {
        createUpdate(INSERT_EXAM, ps -> {
            ps.setString(1, entity.getName());
            ps.setLong(2, entity.getDateExam());
            ps.setLong(3, specialityMapper.getSpecialityIdByName(entity.getSpecialityName()));
        });
        if (entity.getStudentMarkMap() != null) {
            entity.getStudentMarkMap()
                    .forEach((student, mark) -> createUpdate(INSERT_EXAM_MARK, ps -> {
                        ps.setLong(1, student.getId());
                        ps.setLong(2, entity.getId());
                        ps.setString(3, mark.name());
                    }));
        }
        return true;
    }

    @Override
    public boolean updateEntity(Exam entity) {
        return false;
    }

    @Override
    public Optional<Exam> getEntityById(Long id) {
        return Optional.empty();
    }
}
