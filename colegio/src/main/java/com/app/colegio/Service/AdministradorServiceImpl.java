package com.app.colegio.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.colegio.Model.Administrador;
import com.app.colegio.Repository.AdministradorRespository;


@Service
public class AdministradorServiceImpl implements AdministradorService{
    
    @Autowired
    private AdministradorRespository administradorRepository;

    @Override
    @Transactional(readOnly = true)
    public Administrador findByEmailAndPassword(String email, String password) {
        return administradorRepository.findByEmailAndPassword(email, password);
    }
    
}
