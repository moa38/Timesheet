package springData.repository;

import org.springframework.data.repository.CrudRepository;

import springData.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {
   User findByUserId(int userId);
}
