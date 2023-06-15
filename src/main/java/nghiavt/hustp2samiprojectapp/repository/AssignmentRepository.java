package nghiavt.hustp2samiprojectapp.repository;

import nghiavt.hustp2samiprojectapp.constant.AssignStatusEnum;
import nghiavt.hustp2samiprojectapp.model.entity.Assignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, String> {
    List<Assignment> findByAssignId(String appId);
    List<Assignment> findByStudentId(Integer studentId);
    List<Assignment> findByStatus(AssignStatusEnum status);

    final String query = "SELECT a.* FROM assignment a " +
            "INNER JOIN teacher t " +
            "ON a.instructor_id = t.teacher_id " +
            "WHERE t.email = ?1";

    @Query(nativeQuery = true, value = query)
    List<Object[]> getTeacherAsgm(String email);

    @Query(nativeQuery = true, value = query)
    Collection<teacherAssign> getTeacherAsgm1(String email);
    interface teacherAssign{
        String getAssign_id();
        String getApp_id();
        String getComm_id();
        String getInstructor_id();
        String getProject_type();
        String getProject_id();
        String getReviewer_id();
        String getStatus();
        Integer getStudent_id();
        String getTerm();
    }

}
