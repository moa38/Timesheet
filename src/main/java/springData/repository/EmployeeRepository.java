package springData.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import springData.domain.Employee;

public interface EmployeeRespoitory extends CrudRepository <Employee, Integer>{
    Employee findById(int id);
    List<Employee> findByName(String name);
}