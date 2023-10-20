package com.app.colegio.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.colegio.Model.Administrador;
import com.app.colegio.Service.AdministradorService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/acceder")
public class LoginController {
    
    @Autowired
    private AdministradorService administradorService;

    @GetMapping("")
    public String acceder(Map<String,Object> model) {
        Administrador administrador=new Administrador();
        model.put("administrador",administrador);
        return "home/ingresar";
    }

    @PostMapping("")
    public String ingresar(HttpSession session,@Valid Administrador administrador,BindingResult bindingResult,Map<String,Object> model){
        if(bindingResult.hasErrors()){
            model.put("administrador",administrador);
            return "home/ingresar";
        }else{
            Administrador admin=administradorService.findByEmailAndPassword(administrador.getEmail(), administrador.getPassword());
            if(admin!=null){
                session.setAttribute("usuario", admin.getEmail());
                return "redirect:/asignatura/listar";
            }else{
                model.put("administrador",administrador);
                model.put("errorAutenticacion","Correo o contraseña incorrecta");
                return "home/ingresar";
            }
        }
    }

    @GetMapping("/cerrar")
    public String salir(HttpSession session){
        session.removeAttribute("usuario");
        return "redirect:/";
    }
}
