package mvcapp.dao;

import mvcapp.damain.CriteriaCustomer;
import mvcapp.damain.Customer;

import java.util.List;

public interface CustomerDAO {

    /**
     * 得到条件查询的List
     * @param cc 封装了条件的对象
     * @return List
     */
    public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc);

    /**
     * 得到数据库中所有的Customer的List
     * @return
     */
    public List<Customer> getAll();


    /**
     * 存取一个Customer到数据库中
     * @param customer
     */
    public void save(Customer customer);

    /**
     * 通过 id 得到该id所对应的Customer
     * @param id 用户的id
     * @return
     */
    public Customer get(Integer id);

    /**
     * 从数据库删除对应 id 的Customer
     * @param id
     */
    public void delete(Integer id);

    /**
     * 返回和name相等的记录数
     *
     * @param name
     * @return
     */
    public Long getCountWithName(String name);
}
