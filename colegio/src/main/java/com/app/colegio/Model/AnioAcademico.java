package com.app.colegio.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "AnioAcademico")
public class AnioAcademico {

    @Id
    @Column(name = "Id_AnioAcademico")
    @Size(max = 4, min = 4, message = "El código debe tener 4 dígitos")
    private String id;
}
