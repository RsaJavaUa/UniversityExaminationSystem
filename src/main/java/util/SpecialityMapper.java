package util;

import dao.SpecialityDao;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class SpecialityMapper {

    private SpecialityDao specialityDao = new SpecialityDao();
    private Map<String, Long> specialityNameIdMap;

    public SpecialityMapper() {
        specialityNameIdMap = new HashMap<>();
        specialityDao.getAll().forEach(spec -> specialityNameIdMap.put(spec.getName(), spec.getId()));
    }

    public Long getSpecialityIdByName(String specialityName) {
        return specialityName == null ? returnDefault() : specialityNameIdMap.get(specialityName);
    }

    private Long returnDefault() {
        return 1L;
    }

    private <K, V> Stream<K> keys(Map<K, V> map, V value) {
        return map
                .entrySet()
                .stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(Map.Entry::getKey);
    }

    public String fromIdToNameSpeciality(Long id) {
        return keys(specialityNameIdMap, id).findFirst().get();
    }
}
