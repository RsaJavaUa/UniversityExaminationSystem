package entities;

import enums.Mark;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@Accessors (chain = true)
public class Exam {
    private Long id;
    private String name;
    private String specialityName;
    private Long dateExam;
    private Map<User, Mark> studentMarkMap = new HashMap<>();
}
