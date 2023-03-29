package bo.edu.ucb.tallerdedesarollo.backend;

import org.springframework.data.repository.CrudRepository;

public interface CustomerDAO extends CrudRepository<Customer, Long> {

}