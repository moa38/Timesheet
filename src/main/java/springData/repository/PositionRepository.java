package springData.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import springData.domain.Position;

public interface PositionRepository extends CrudRepository<Position, Integer> {

    Position findByPosition(String posName);
}