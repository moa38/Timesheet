package springData.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import springData.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUserId(int UserId);

    //List<User> findByName(String name);
}