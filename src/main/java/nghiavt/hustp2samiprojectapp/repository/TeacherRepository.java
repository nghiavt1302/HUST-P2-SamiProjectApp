package nghiavt.hustp2samiprojectapp.repository;

import nghiavt.hustp2samiprojectapp.constant.DepartmentEnum;
import nghiavt.hustp2samiprojectapp.model.dataObject.TeacherListForApplying;
import nghiavt.hustp2samiprojectapp.model.entity.Student;
import nghiavt.hustp2samiprojectapp.model.entity.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {
    List<Teacher> findAllByDepartment(DepartmentEnum department);
    List<Teacher> findAllByExpertise(String expertise);

    List<Teacher> findByTeacherId(String teacherId);
    List<Teacher> findByEmail(String email);
}
