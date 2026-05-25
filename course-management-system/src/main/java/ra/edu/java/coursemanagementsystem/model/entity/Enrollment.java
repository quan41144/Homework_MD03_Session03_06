package ra.edu.java.coursemanagementsystem.model.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Enrollment {
    private Long id;
    private String studentName;
    private Long courseId;
}
