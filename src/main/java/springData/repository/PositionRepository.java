package springData.repository;

import org.springframework.data.repository.CrudRepository;

import springData.domain.Position;

public interface PositionRepository extends CrudRepository<Position, String> {
   Position findByPositionName(String positionName);
}
