package mvcapp.dao.impl;

import mvcapp.damain.CriteriaCustomer;
import mvcapp.damain.Customer;
import mvcapp.dao.CustomerDAO;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CustomerDAOImplTest {

    private CustomerDAO customerDAO = new CustomerDAOImpl();
    @Test
    public void testGetAll(){
        List<Customer> customers = customerDAO.getAll();
        System.out.println(customers);

    }
    @Test
    public void getForValue() {
    }

    @Test
    public void getForList() {
    }

    @Test
    public void get() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void save() {
        Customer customer = new Customer();
        customer.setAddress("Beijing");
        customer.setName("Mike");
        customer.setPhone("143872583");
        customerDAO.save(customer);
    }

    @Test
    public void get1() {
    }

    @Test
    public void delete() {
        customerDAO.delete(1);
    }

    @Test
    public void getCountWithName() {
        Long count = customerDAO.getCountWithName("Mike");
        System.out.println(count);
    }

    @Test
    public void getForListWithCriteriaCustomer() {
        CriteriaCustomer cc = new CriteriaCustomer(null, null ,null);
        List<Customer> customers = customerDAO.getForListWithCriteriaCustomer(cc);
        System.out.println(customers);
    }
}