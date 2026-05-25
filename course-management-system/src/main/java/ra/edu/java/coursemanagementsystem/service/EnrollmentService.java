package ra.edu.java.coursemanagementsystem.service;

import ra.edu.java.coursemanagementsystem.model.dto.request.EnrollCourseRequest;
import ra.edu.java.coursemanagementsystem.model.dto.response.EnrollmentDetail;
import ra.edu.java.coursemanagementsystem.model.entity.Enrollment;

import java.util.List;

public interface EnrollmentService {
    List<Enrollment> getAllEnrollments();
    Enrollment getEnrollmentById(Long id);
    Enrollment createEnrollment(Enrollment enrollment);
    Enrollment updateEnrollment(Long id, Enrollment enrollment);
    void deleteEnrollmentById(Long id);
    EnrollmentDetail enrollCourse(EnrollCourseRequest request);
}
