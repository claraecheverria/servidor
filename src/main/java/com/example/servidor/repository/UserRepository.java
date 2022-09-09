package com.example.servidor.repository;

import com.example.servidor.model.User;
import org.springframework.data.repository.CrudRepository;

//@Repository no se necesita ya está incluido en jpaRepo
public interface UserRepository extends CrudRepository<User, Long> {

}
