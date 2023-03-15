package nghiavt.hustp2samiprojectapp.repository;

import nghiavt.hustp2samiprojectapp.model.entity.TeacherInComm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherInCommRepository extends JpaRepository<TeacherInComm, Integer> {
}
