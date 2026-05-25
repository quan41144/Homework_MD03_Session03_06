package ra.edu.java.coursemanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.java.coursemanagementsystem.model.dto.request.EnrollCourseRequest;
import ra.edu.java.coursemanagementsystem.model.dto.response.EnrollmentDetail;
import ra.edu.java.coursemanagementsystem.model.entity.Course;
import ra.edu.java.coursemanagementsystem.model.entity.Enrollment;
import ra.edu.java.coursemanagementsystem.model.entity.Instructor;
import ra.edu.java.coursemanagementsystem.repository.CourseRepo;
import ra.edu.java.coursemanagementsystem.repository.EnrollmentRepo;
import ra.edu.java.coursemanagementsystem.repository.InstructorRepo;
import ra.edu.java.coursemanagementsystem.service.EnrollmentService;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepo enrollmentRepo;
    private final CourseRepo courseRepo;
    private final InstructorRepo instructorRepo;
    @Autowired
    public EnrollmentServiceImpl(EnrollmentRepo enrollmentRepo, CourseRepo courseRepo, InstructorRepo instructorRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.courseRepo = courseRepo;
        this.instructorRepo = instructorRepo;
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepo.findAll();
    }

    @Override
    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment with id " + id + " not found!"));
    }

    @Override
    public Enrollment createEnrollment(Enrollment enrollment) {
        return enrollmentRepo.create(enrollment);
    }

    @Override
    public Enrollment updateEnrollment(Long id, Enrollment enrollment) {
        return enrollmentRepo.update(id, enrollment);
    }

    @Override
    public void deleteEnrollmentById(Long id) {
        enrollmentRepo.deleteById(id);
    }

    @Override
    public EnrollmentDetail enrollCourse(EnrollCourseRequest request) {
        Course course = courseRepo.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found!"));
        if (!"ACTIVE".equalsIgnoreCase(course.getStatus())) {
            throw new RuntimeException("Course status is not ACTIVE");
        }
        Instructor instructor = instructorRepo.findById(course.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found!"));
        Long newId = enrollmentRepo.findAll().stream()
                .mapToLong(Enrollment::getId)
                .max()
                .orElse(1000) + 1;
        Enrollment enrollment = Enrollment.builder()
                .id(newId)
                .studentName(request.getStudentName())
                .courseId(course.getId())
                .build();
        enrollmentRepo.create(enrollment);
        return EnrollmentDetail.builder()
                .enrollmentId(enrollment.getId())
                .studentName(enrollment.getStudentName())
                .courseId(enrollment.getCourseId())
                .courseTitle(course.getTitle())
                .instructorName(instructor.getName())
                .build();
    }
}
