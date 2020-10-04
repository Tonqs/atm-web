package th.ac.ku.ATM.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import th.ac.ku.ATM.Data.CustomerRespository;
import th.ac.ku.ATM.model.Customer;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {
    private CustomerRespository respository;


    public CustomerService(CustomerRespository respository) {
        this.respository = respository;
    }

    public void createCustomer(Customer customer){
        //hash pin for customer
        String hashPin = hash(customer.getPin());
        customer.setPin(hashPin);
        respository.save(customer);
    }

    public List<Customer> getCustomer(){
        return respository.findAll();
    }

    public Customer findCustomer(int id) {
        try {
            return respository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }

    }

    public Customer checkPin(Customer inputCustomer) {
        // 1. หา customer ที่มี id ตรงกับพารามิเตอร์
        Customer storedCustomer = findCustomer(inputCustomer.getId());

        // 2. ถ้ามี id ตรง ให้เช็ค pin ว่าตรงกันไหม โดยใช้ฟังก์ชันเกี่ยวกับ hash
        if (storedCustomer != null) {
            String hashPin = storedCustomer.getPin();

            if (BCrypt.checkpw(inputCustomer.getPin(), hashPin))
                return storedCustomer;
        }
        // 3. ถ้าไม่ตรง ต้องคืนค่า null
        return null;
    }

    private String hash(String pin){
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(pin, salt); // process hash
    }
}
