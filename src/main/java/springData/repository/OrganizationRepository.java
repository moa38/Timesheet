package springData.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import springData.domain.Organization;

public interface OrganizationRespoitory extends CrudRepository <Organization, Integer>{
    Organization findById(int id);
    List<Organization> findByName(String name);
}