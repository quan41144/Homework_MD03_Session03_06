package ra.edu.java.coursemanagementsystem.service;

import ra.edu.java.coursemanagementsystem.model.dto.response.InstructorDetailResponse;
import ra.edu.java.coursemanagementsystem.model.entity.Instructor;

import java.util.List;

public interface InstructorService {
    List<Instructor> getAllInstructors();
    Instructor getInstructorById(Long id);
    Instructor createInstructor(Instructor instructor);
    Instructor updateInstructor(Long id, Instructor instructor);
    void deleteInstructorById(Long id);
    List<InstructorDetailResponse> getInstructorWithActiveAndEnrolledCourses();
}
