package nghiavt.hustp2samiprojectapp.repository;

import nghiavt.hustp2samiprojectapp.model.entity.MyUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<MyUser, Integer> {
    List<MyUser> findByEmail(String email);
}
