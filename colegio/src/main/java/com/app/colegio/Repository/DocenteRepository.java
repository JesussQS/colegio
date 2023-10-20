package com.app.colegio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.colegio.Model.Docente;

public interface DocenteRepository extends JpaRepository<Docente, String> {

}
