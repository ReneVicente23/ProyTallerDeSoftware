package bo.edu.ucb.tallerdedesarollo.backend.DAO;

import bo.edu.ucb.tallerdedesarollo.backend.DTO.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDAO extends CrudRepository<Customer, Long> {

}