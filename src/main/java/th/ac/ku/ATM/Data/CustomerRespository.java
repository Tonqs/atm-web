package th.ac.ku.ATM.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.ac.ku.ATM.model.Customer;


@Repository
public interface CustomerRespository extends JpaRepository<Customer, Integer> {

}


