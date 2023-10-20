package com.app.colegio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.colegio.Model.Administrador;

public interface AdministradorRespository extends JpaRepository<Administrador,Integer> {
    
    Administrador findByEmailAndPassword(String email,String password);
}
