package com.example.servidor.repository;

import com.example.servidor.model.User;
import com.example.servidor.model.UserEmpleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    @Query(value = "SELECT * FROM users u WHERE u.email = :email and u.password = :password",
            nativeQuery = true)
    List<String[]> findUserByEmailAndPasswordNamedParamsNative(
            @Param("email") String email, @Param("password") String password);

    @Query(value = "SELECT * FROM users u where u.email = :email and u.type = EMPLEADO", nativeQuery = true)
    UserEmpleado findUserEmpleadoByEmail(@Param("email") String email);

    @Query(value = "SELECT u.email , u.cedula ,u.nombre ,u.\"password\" ,u.telefono ,u.saldo ,u.vencimiento_carne ,u.centro_dep_nombre ,u.empresa_nombre FROM users u where u.empresa_nombre = :empresaNombre and u.type = 'EMPLEADO'", nativeQuery = true)
    List<UserEmpleado> findUserEmpleadosByEmpresa(@Param("empresaNombre") String empresaNombre);



}
