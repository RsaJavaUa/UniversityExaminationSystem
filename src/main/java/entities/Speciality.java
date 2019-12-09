package entities;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Data
@Accessors (chain = true)
public class Speciality {
    private Long id;
    private String name;
    private Set<Exam> exams = new HashSet<>();
}
