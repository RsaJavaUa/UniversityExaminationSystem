package entities;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Speciality {
    private Long id;
    private String name;
    private Set<Exam> exams = new HashSet<>();
}
