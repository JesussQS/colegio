package com.app.colegio.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.colegio.Model.AnioAcademico;
import com.app.colegio.Model.Asignatura;
import com.app.colegio.Model.PlanEstudios;
import com.app.colegio.Repository.AnioAcademicoRepository;
import com.app.colegio.Repository.AsignaturaRepository;
import com.app.colegio.Repository.PlanEstudiosRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/plan")
public class PlanEstudiosController {

    @Autowired
    private PlanEstudiosRepository planEstudiosRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private AnioAcademicoRepository anioAcademicoRepository;

    @GetMapping("/listar")
    public String listar(HttpSession session, Map<String, Object> model) {
        if (session.getAttribute("usuario") != null) {
            model.put("anioAcademicos", anioAcademicoRepository.findAll());
            return "plan/listarPlan";
        } else {
            return "redirect:/acceder";
        }
    }

    @GetMapping("/crear")
    public String crear(HttpSession session, Map<String, Object> model) {
        if (session.getAttribute("usuario") != null) {
            AnioAcademico anioAcademico = new AnioAcademico();
            List<Asignatura> asignatura = asignaturaRepository.findAll();
            model.put("anioAcademico", anioAcademico);
            model.put("asignaturaList", asignatura);
            return "plan/crearPlan";
        } else {
            return "redirect:/acceder";
        }
    }

    @GetMapping("/ver/{id_AnioAcademico}")
    public String ver(HttpSession session, @PathVariable String idAnioAcademico, Map<String, Object> model) {
        if (session.getAttribute("usuario") != null) {
            AnioAcademico anioAcademico = anioAcademicoRepository.findById(idAnioAcademico).orElse(null);
            if (anioAcademico != null) {
                model.put("anioAcademico", anioAcademico);
                List<PlanEstudios> planes = planEstudiosRepository.findByAnioAcademico(anioAcademico);
                List<Map<String, Object>> asignaturasConHoras = new ArrayList<>();
                for (PlanEstudios plan : planes) {
                    Map<String, Object> asignaturaConHoras = new HashMap<>();
                    asignaturaConHoras.put("asignatura", plan.getAsignatura());
                    asignaturaConHoras.put("horasSemanales", plan.getHorasSemanales());
                    asignaturasConHoras.add(asignaturaConHoras);
                }
                model.put("asignaturasConHoras", asignaturasConHoras);
                return "plan/verPlan";
            } else {
                return "redirect:/plan/listar";
            }
        } else {
            return "redirect:/acceder";
        }
    }

}
