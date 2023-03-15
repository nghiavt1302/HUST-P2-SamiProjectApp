package nghiavt.hustp2samiprojectapp.repository.dataObjectRepository;

import nghiavt.hustp2samiprojectapp.model.dataObject.Parameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParametersRepo extends JpaRepository<Parameters, String> {
    List<Parameters> findByCurrent(Boolean current);
    List<Parameters> findByTerm(String term);
}
