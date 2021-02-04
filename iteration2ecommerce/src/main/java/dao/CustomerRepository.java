package dao;
import entity.Employe;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Employe, Integer> {


	
}
