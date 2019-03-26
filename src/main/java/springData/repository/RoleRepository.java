package springData.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springData.domain.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> {

   Role findById(int id);

}
