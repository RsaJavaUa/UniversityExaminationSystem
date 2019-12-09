package dao.interfaces;

import java.util.List;
import java.util.Optional;

public interface DaoInterface<T> {
    abstract List<T> getAll();

    abstract boolean deleteEntity(T entity);

    abstract boolean saveEntity(T entity);

    abstract boolean updateEntity(T entity);

    abstract Optional<T> getEntityById(Long id);
}
