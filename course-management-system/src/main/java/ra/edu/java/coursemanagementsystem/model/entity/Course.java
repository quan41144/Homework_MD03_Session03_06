package ra.edu.java.coursemanagementsystem.model.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Course {
    private Long id;
    private String title;
    private String status;
    private Long instructorId;
}
