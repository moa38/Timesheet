package springData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springData.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
   User findById(int id);
   
   @SuppressWarnings("unchecked")
   User save(User user);
}
