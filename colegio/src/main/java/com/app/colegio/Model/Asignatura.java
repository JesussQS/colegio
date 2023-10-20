package com.app.colegio.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Asignatura")
public class Asignatura {

    @Id
    @Column(name = "Id_Asignatura")
    @Size(max = 7, min = 7, message = "El código debe tener 7 dígitos")
    private String id;

    @NotBlank(message = "El campo es obligatorio")
    @NotNull
    private String descripcion;

}
