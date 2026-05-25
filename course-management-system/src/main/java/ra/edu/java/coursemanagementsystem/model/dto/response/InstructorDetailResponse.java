package ra.edu.java.coursemanagementsystem.model.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InstructorDetailResponse {
    private Long instructorId;
    private String instructorName;
    private String email;
    private List<CourseDTO> activeCourses;
}
