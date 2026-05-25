package ra.edu.java.coursemanagementsystem.model.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseDTO {
    private Long courseId;
    private String courseTitle;
    private String status;
}
