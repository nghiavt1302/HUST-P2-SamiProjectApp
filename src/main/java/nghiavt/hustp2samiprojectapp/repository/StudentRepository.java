package nghiavt.hustp2samiprojectapp.repository;

import nghiavt.hustp2samiprojectapp.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByStudentId(Integer id);
    List<Student> findAllByClassName(String className);
    List<Student> findByEmail(String email);
}
