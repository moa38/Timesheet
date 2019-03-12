package springData.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import springData.domain.Organization;
import springData.domain.User;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
   //List<Timesheet> findById(int id);
   
   @Query("Select s from Organization s where s.organizationId= :id")
   List<Organization> findById(@Param("id") int id);
   
   Organization findByName(String name);

   @SuppressWarnings("unchecked")
   Organization save(Organization organization);
}
