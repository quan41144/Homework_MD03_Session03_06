package ra.edu.java.coursemanagementsystem.model.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EnrollmentDetail {
    private Long enrollmentId;
    private String studentName;
    private Long courseId;
    private String courseTitle;
    private String instructorName;
}
