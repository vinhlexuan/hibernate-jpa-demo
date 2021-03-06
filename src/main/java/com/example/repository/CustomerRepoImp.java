package com.example.repository;

import com.example.model.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CustomerRepoImp implements CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c",Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findbyID(Long id) {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c where c.id = :id",Customer.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public void save(Customer model) {
        if (model.getId()!=null)
            em.merge(model);
        else em.persist(model);
    }
    @Override
    public void remove(Long id) {
        Customer customer = this.findbyID(id);
        em.remove(customer);
    }
}
