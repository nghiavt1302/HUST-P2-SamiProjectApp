package nghiavt.hustp2samiprojectapp.repository.dataObjectRepository;

import nghiavt.hustp2samiprojectapp.model.dataObject.TeachersNameList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachersNameListRepo extends JpaRepository<TeachersNameList, String> {

}
