package nghiavt.hustp2samiprojectapp.repository;

import nghiavt.hustp2samiprojectapp.constant.AssignStatusEnum;
import nghiavt.hustp2samiprojectapp.model.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, String> {
    List<Assignment> findByAssignId(String appId);
    List<Assignment> findByStudentId(Integer studentId);
    List<Assignment> findByStatus(AssignStatusEnum status);
}
