package ra.edu.java.coursemanagementsystem.repository;

import ra.edu.java.coursemanagementsystem.model.entity.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepo {
    List<Enrollment> findAll();
    Optional<Enrollment> findById(Long id);
    Enrollment create(Enrollment enrollment);
    Enrollment update(Long id, Enrollment enrollment);
    void deleteById(Long id);
}
