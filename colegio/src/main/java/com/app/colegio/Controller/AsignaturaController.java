package com.app.colegio.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.colegio.Model.Asignatura;
import com.app.colegio.Repository.AsignaturaRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/asignatura")
public class AsignaturaController {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @GetMapping("/listar")
    public String listar(HttpSession session, Map<String, Object> model) {
        if (session.getAttribute("usuario") != null) {
            model.put("asignaturas", asignaturaRepository.findAll());
            return "asignatura/listarAsignatura";
        } else {
            return "redirect:/acceder";
        }
    }

    @GetMapping("/crear")
    public String crear(HttpSession session, Map<String, Object> model) {
        if (session.getAttribute("usuario") != null) {
            Asignatura asignatura = new Asignatura();
            model.put("asignatura", asignatura);

            return "asignatura/crearAsignatura";
        } else {
            return "redirect:/acceder";
        }
    }

    @PostMapping("/crear")
    public String guardar(HttpSession session, @Valid Asignatura asignatura, BindingResult bindingResult,
            Map<String, Object> model) {
        if (session.getAttribute("usuario") != null) {
            if (bindingResult.hasErrors()) {
                asignatura.setDescripcion(asignatura.getDescripcion().trim());
                model.put("asignatura", asignatura);
                return "asignatura/crearAsignatura";
            } else {
                asignaturaRepository.save(asignatura);
                return "redirect:/asignatura/listar";
            }

        } else {
            return "redirect:/acceder";
        }
    }

    @GetMapping("/actualizar/{id}")
    public String enviar(HttpSession session, @PathVariable String id, Map<String, Object> model) {
        if (session.getAttribute("usuario") != null) {
            try {
                Optional<Asignatura> asignatura = asignaturaRepository.findById(id);
                if (asignatura.isPresent()) {
                    model.put("asignatura", asignatura);
                    return "asignatura/editarAsignatura";
                } else {
                    return "redirect:/asignatura/listar";
                }
            } catch (Exception e) {
                return "redirect:/asignatura/listar";
            }
        } else {
            return "redirect:/asignatura/listar";
        }
    }

    @PostMapping("/actualizar")
    public String guardarCambios(HttpSession session, @Valid Asignatura asignatura, BindingResult bindingResult,
            Map<String, Object> model) {
        if (session.getAttribute("usuario") != null) {
            if (bindingResult.hasErrors()) {
                model.put("asignatura", asignatura);
                return "asignatura/editarAsignatura";
            } else {
                try {
                    Optional<Asignatura> asignaturaB = asignaturaRepository.findById(asignatura.getId());
                    if (asignaturaB.isPresent()) {
                        asignaturaRepository.save(asignatura);
                        return "redirect:/asignatura/listar";
                    } else {
                        return "asignatura/editarAsignatura";
                    }
                } catch (Exception e) {
                    model.put("asignatura", asignatura);
                    return "asignatura/editarAsignatura";
                }
            }
        } else {
            return "redirect:/asignatura/listar";
        }
    }
}
