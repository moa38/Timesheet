package springData.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import springData.domain.Employees;

public interface EmployeeRepository extends CrudRepository<Employees, Integer> {
    Employees findById(int id);

    List<Employees> findByName(String name);
}