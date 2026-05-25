package ra.edu.java.coursemanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.java.coursemanagementsystem.model.dto.response.CourseDTO;
import ra.edu.java.coursemanagementsystem.model.dto.response.InstructorDetailResponse;
import ra.edu.java.coursemanagementsystem.model.entity.Course;
import ra.edu.java.coursemanagementsystem.model.entity.Enrollment;
import ra.edu.java.coursemanagementsystem.model.entity.Instructor;
import ra.edu.java.coursemanagementsystem.repository.CourseRepo;
import ra.edu.java.coursemanagementsystem.repository.EnrollmentRepo;
import ra.edu.java.coursemanagementsystem.repository.InstructorRepo;
import ra.edu.java.coursemanagementsystem.service.InstructorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepo instructorRepo;
    private final CourseRepo courseRepo;
    private final EnrollmentRepo enrollmentRepo;
    @Autowired
    public InstructorServiceImpl(InstructorRepo instructorRepo, CourseRepo courseRepo, EnrollmentRepo enrollmentRepo) {
        this.instructorRepo = instructorRepo;
        this.courseRepo = courseRepo;
        this.enrollmentRepo = enrollmentRepo;
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepo.findAll();
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor with id " + id + " not found"));
    }

    @Override
    public Instructor createInstructor(Instructor instructor) {
        return instructorRepo.create(instructor);
    }

    @Override
    public Instructor updateInstructor(Long id, Instructor instructor) {
        return instructorRepo.update(id, instructor);
    }

    @Override
    public void deleteInstructorById(Long id) {
        instructorRepo.deleteById(id);
    }

    @Override
    public List<InstructorDetailResponse> getInstructorWithActiveAndEnrolledCourses() {
        List<Instructor> allInstructors = instructorRepo.findAll();
        List<Course> allCourses = courseRepo.findAll();
        List<Enrollment> allEnrollments = enrollmentRepo.findAll();
        return allInstructors.stream().map(instructor -> {
            List<CourseDTO> courseDTOS = allCourses.stream()
                    .filter(course -> course.getInstructorId().longValue() == instructor.getId()
                    && "ACTIVE".equalsIgnoreCase(course.getStatus()))
                    .filter(course -> allEnrollments.stream()
                            .anyMatch(enrollment -> enrollment.getCourseId().longValue() == course.getId()))
                    .map(course -> CourseDTO.builder()
                            .courseId(course.getId())
                            .courseTitle(course.getTitle())
                            .status(course.getStatus())
                            .build())
                    .collect(Collectors.toList());
            return InstructorDetailResponse.builder()
                    .instructorId(instructor.getId())
                    .instructorName(instructor.getName())
                    .email(instructor.getEmail())
                    .activeCourses(courseDTOS)
                    .build();
        }).collect(Collectors.toList());
    }
}
