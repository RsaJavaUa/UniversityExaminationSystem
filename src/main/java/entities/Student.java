package entities;

import enums.Role;
import lombok.Data;

@Data
public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
