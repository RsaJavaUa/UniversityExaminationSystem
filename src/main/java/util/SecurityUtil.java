package util;

import enums.Role;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecurityUtil {

    private static Map<Role, List<String>> securityConfigMap = new HashMap<>();

    static {
        securityConfigMap.put(Role.STUDENT, Arrays.asList("/university", "/speciality", "/exam"));
        securityConfigMap.put(Role.ADMIN, Arrays.asList("/admin"));
    }

    public static boolean isSecurePage(String page) {
        return securityConfigMap.values().stream()
                .anyMatch(list -> list.stream()
                        .anyMatch(pageValue -> pageValue.equals(page)));
    }

    public static boolean hasPermission(String page, Role role) {
        return securityConfigMap.getOrDefault(role, Collections.EMPTY_LIST)
                .stream()
                .anyMatch(securePage -> securePage.equals(page));
    }
}
