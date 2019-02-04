package springData.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import springData.domain.UserAcc;

public interface UserAccRespoitory extends CrudRepository <UserAcc, Integer>{
   
    UserAcc findById(int id);

    List<UserAcc> findByName(String name);
}