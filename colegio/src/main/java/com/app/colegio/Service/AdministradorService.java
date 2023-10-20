package com.app.colegio.Service;

import com.app.colegio.Model.Administrador;

public interface AdministradorService {
    
    Administrador findByEmailAndPassword(String email,String password);
}
