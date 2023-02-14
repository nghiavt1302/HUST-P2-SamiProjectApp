package nghiavt.hustp2samiprojectapp.repository.dataObjectRepository;

import nghiavt.hustp2samiprojectapp.model.dataObject.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TermRepo extends JpaRepository<Term, String> {
    List<Term> findByCurrent(Boolean current);
}
