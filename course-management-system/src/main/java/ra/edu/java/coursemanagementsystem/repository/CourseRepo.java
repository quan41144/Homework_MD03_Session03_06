package ra.edu.java.coursemanagementsystem.repository;

import ra.edu.java.coursemanagementsystem.model.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepo {
    List<Course> findAll();
    Optional<Course> findById(Long id);
    Course create(Course course);
    Course update(Long id, Course course);
    void deleteById(Long id);
}
