package ra.edu.java.coursemanagementsystem.model.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EnrollCourseRequest {
    private String studentName;
    private Long courseId;
}
