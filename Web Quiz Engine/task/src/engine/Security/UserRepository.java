package engine.Security;

import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface UserRepository extends CrudRepository<User, String> {
}