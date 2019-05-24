package mvcapp.dao.impl;

import mvcapp.damain.CriteriaCustomer;
import mvcapp.damain.Customer;
import mvcapp.dao.CustomerDAO;
import mvcapp.dao.DAO;

import java.util.List;

/**
 * 还没实现
 */
public class CustomerDAOImpl extends DAO<Customer> implements CustomerDAO {

    @Override
    public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc) {
        String sql = "select id, name, address, phone from customers " +
                "where name like ? and address like ? AND phone like ? ";
        return getForList(sql, cc.getName(), cc.getAddress(), cc.getPhone());
    }

    @Override
    public List<Customer> getAll() {
        String sql = "select id, name, address, phone from customers";
        return getForList(sql);
    }

    @Override
    public void save(Customer customer) {
        String sql = "insert into customers(name, address, phone) values" +
                "(?,?,?)";
        update(sql, customer.getName(),customer.getAddress(),customer.getPhone());
    }

    @Override
    public Customer get(Integer id) {
        String sql = "select id, name, phone from customers where id = ?";
        return get(sql, id);
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from customers where id = ?";
        update(sql, id);
    }

    @Override
    public Long getCountWithName(String name) {
        String sql = "select count(*) from customers where name = ?";
        return getForValue(sql, name);
    }
}
