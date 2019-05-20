package mvcapp.dao;

import mvcapp.damain.Customer;

import java.util.List;

public interface CustomerDAO  {
    public List<Customer> getAll();

    public void save(Customer customer);

    public Customer get(Integer id);

    public void delete(Integer id);

    /**
     * 返回和name相等的记录数
     * @param name
     * @return
     */
    public Long getCountWithName(String name);
}
