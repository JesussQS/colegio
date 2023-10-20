package com.app.colegio.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "Administrador")
@Entity
public class Administrador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Admin")
    private Integer id;

    @NotNull
    @NotBlank(message = "El campo es obligatorio")
    @Email(message = "Formato incorrecto")
    private String email;

    @NotBlank(message = "El campo es obligatorio")
    @NotNull
    private String password;

}
