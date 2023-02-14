package nghiavt.hustp2samiprojectapp.repository.dataObjectRepository;

import nghiavt.hustp2samiprojectapp.model.dataObject.TeacherListForApplying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherListForApplyingRepo extends JpaRepository<TeacherListForApplying, String> {
}
