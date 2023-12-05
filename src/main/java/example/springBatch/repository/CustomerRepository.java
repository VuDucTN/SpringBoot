package example.springBatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import example.springBatch.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
