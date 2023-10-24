package com.app.colegio.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PlanEstudios")

public class PlanEstudios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_PlanEstudios")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Id_Asignatura")
    private Asignatura asignatura;

    @ManyToOne
    @JoinColumn(name = "Id_AnioAcademico")
    private AnioAcademico anioAcademico;

    @NotBlank(message = "El campo es obligatorio")
    @NotNull
    private Integer horasSemanales;
}
