package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CustomerRepository;
import entity.Employe;
@Service("customerService")
public class CustomerService {
@Autowired
    private CustomerRepository repository;

    public void test() {
        Employe e = new Employe();
        e.setNom("titi");
        repository.save(e);
    }

}