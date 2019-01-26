package CO2015Group17.CW1;

import org.springframework.data.repository.CrudRepository;

import CO2015Group17.CW1.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

}