package springData.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import springData.domain.Shifts;

public interface ShiftsRespoitory extends CrudRepository <Shifts, Integer>{
    Shifts findById(int id);
   
}