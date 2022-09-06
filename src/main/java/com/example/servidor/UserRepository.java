package com.example.servidor;

import org.springframework.data.jpa.repository.JpaRepository;

//@Repository no se necesita ya está incluido en jpaRepo
public interface UserRepository extends JpaRepository<User, Long> {

}
