package ra.edu.java.coursemanagementsystem.service;

import ra.edu.java.coursemanagementsystem.model.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    Course getCourseById(Long id);
    Course createCourse(Course course);
    Course updateCourse(Long id, Course course);
    void deleteCourseById(Long id);
}
