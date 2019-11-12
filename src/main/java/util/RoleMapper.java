package util;

import dao.SpecialityDao;
import enums.Role;

import java.util.HashMap;
import java.util.Map;

public class RoleMapper {
    private static Map<String, Role> roleStringMap = new HashMap<>();

    static {
        roleStringMap.put("ADMIN", Role.ADMIN);
        roleStringMap.put("USER", Role.USER);
    }

    public static Role getRoleByString(String role) {
        return roleStringMap.get(role.toUpperCase());
    }
}
