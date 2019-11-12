package entities;

import enums.Mark;
import enums.Role;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@ToString(exclude = "examNamesMarks")
@Accessors(chain = true)
public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;

    //transient
    // ask Valery should I put this fields in Student entity
    private String specialityName;
    private Map<String, Mark> examNamesMarks = new HashMap<>();

}
