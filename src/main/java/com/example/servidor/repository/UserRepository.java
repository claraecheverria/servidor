package com.example.servidor.repository;

import com.example.servidor.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {
    @Query(value = "SELECT type, nombre FROM users u WHERE u.email = :email and u.password = :password",
            nativeQuery = true)
    List<String[]> findUserByEmailAndPasswordNamedParamsNative(
            @Param("email") String email, @Param("password") String password);

}
