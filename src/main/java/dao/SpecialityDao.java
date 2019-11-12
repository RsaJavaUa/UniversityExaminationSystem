package dao;

import dao.interfaces.AbstractDao;
import dao.interfaces.ResultSetMapper;
import entities.Speciality;

import java.util.List;
import java.util.Optional;

public class SpecialityDao extends AbstractDao<Speciality> {

    private static final String SELECT_SPECIALITIES_WITHOUT_EXAMS = "SELECT * FROM speciality";

    private ResultSetMapper<Speciality> mapToSpeciality = rs -> new Speciality().setId(rs.getLong(1))
            .setName(rs.getString(2));


    @Override
    public List<Speciality> getAll() {
        return getAll(SELECT_SPECIALITIES_WITHOUT_EXAMS, mapToSpeciality);
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
