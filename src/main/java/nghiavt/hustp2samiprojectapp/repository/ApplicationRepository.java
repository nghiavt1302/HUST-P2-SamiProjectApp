package nghiavt.hustp2samiprojectapp.repository;

import nghiavt.hustp2samiprojectapp.model.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, String> {
    List<Application> findByAppId(String appId);
}
