package com.app.colegio.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.colegio.Model.AnioAcademico;
import com.app.colegio.Model.PlanEstudios;

public interface PlanEstudiosRepository extends JpaRepository<PlanEstudios, String> {
    List<PlanEstudios> findByAnioAcademico(AnioAcademico anioAcademico);

}
