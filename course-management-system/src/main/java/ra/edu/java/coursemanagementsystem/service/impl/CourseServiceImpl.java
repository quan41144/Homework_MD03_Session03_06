package ra.edu.java.coursemanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.java.coursemanagementsystem.model.entity.Course;
import ra.edu.java.coursemanagementsystem.repository.CourseRepo;
import ra.edu.java.coursemanagementsystem.service.CourseService;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepo courseRepo;
    @Autowired
    public CourseServiceImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }


    @Override
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course with id " + id + " not found!"));
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepo.create(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        return courseRepo.update(id, course);
    }

    @Override
    public void deleteCourseById(Long id) {
        courseRepo.deleteById(id);
    }
}
