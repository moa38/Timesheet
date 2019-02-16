package springData.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import springData.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findById(int id);

    //List<User> findByName(String name);
}