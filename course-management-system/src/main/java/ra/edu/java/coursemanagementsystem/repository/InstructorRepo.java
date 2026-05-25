package ra.edu.java.coursemanagementsystem.repository;

import ra.edu.java.coursemanagementsystem.model.entity.Instructor;

import java.util.List;
import java.util.Optional;

public interface InstructorRepo {
    List<Instructor> findAll();
    Optional<Instructor> findById(Long id);
    Instructor create(Instructor instructor);
    Instructor update(Long id, Instructor instructor);
    void deleteById(Long id);
}
