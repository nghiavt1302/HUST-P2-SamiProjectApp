package nghiavt.hustp2samiprojectapp.repository;

import nghiavt.hustp2samiprojectapp.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
    List<Project> findAllByKeywordsContains(String keyword);
    List<Project> findAllByStudentId(Integer id);

}
