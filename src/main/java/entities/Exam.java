package entities;

import enums.Mark;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Exam {
    private Long id;
    private String name;
    private Map<Student, Mark> studentMarkMap = new HashMap<>();
    private Long dateExam;
}
