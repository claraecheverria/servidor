package com.example.servidor.repository;

import com.example.servidor.model.Empresa;
import org.springframework.data.repository.CrudRepository;

public interface EmpresaRepository extends CrudRepository<Empresa, String> {
}
