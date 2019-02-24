package springData.repository;

import org.springframework.data.repository.CrudRepository;

import springData.domain.Shift;

public interface ShiftRepository extends CrudRepository<Shift, Integer> {
    Shift findById(int id);

}