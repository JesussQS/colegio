package com.app.colegio.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Docente")
public class Docente {

    @Id
    @Column(name = "Id_Docente")
    @Size(max = 9, min = 9, message = "El código debe tener 9 dígitos")
    private String id;

    @NotBlank(message = "El campo es obligatorio")
    @NotNull
    private String nombres;

    @NotBlank(message = "El campo es obligatorio")
    @NotNull
    private String apePaterno;

    @NotBlank(message = "El campo es obligatorio")
    @NotNull
    private String apeMaterno;

    @ManyToOne
    @JoinColumn(name = "Id_Asignatura")
    @NotNull(message ="El campo es obligatorio")
    private Asignatura asignatura;

}
