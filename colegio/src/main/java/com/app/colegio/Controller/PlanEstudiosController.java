package com.app.colegio.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
            LocalDate fechaActual = LocalDate.now();
            String anio=fechaActual.getYear()+"";
            List<Asignatura> asignaturas = asignaturaRepository.findAll();
            Integer[]horas=new Integer[9];
            model.put("asignaturas", asignaturas);
            model.put("anio",anio);
            model.put("horas", horas);
            return "plan/crearPlan";
        } else {
            return "redirect:/acceder";
        }
    }

    @PostMapping("/crear")
    public String guardar(HttpSession session, Map<String, Object> model,Integer horas[]){
        if (session.getAttribute("usuario") != null) {

            Boolean flag=false;
            LocalDate fechaActual = LocalDate.now();
            String anio=fechaActual.getYear()+"";
            List<Asignatura> asignaturas = asignaturaRepository.findAll();

            for(int i=0;horas.length>i;i++){
                if(horas[i]==null){
                    flag=true;
                }else{
                    if(horas[i]<2 || horas[i]>4){
                        flag=true;
                    }
                }
            }
            if(flag){
                model.put("asignaturas", asignaturas);
                model.put("anio",anio);
                model.put("horas", horas);
                model.put("alertaCampos", "Todos los campos son obligatorios y el rango debe estar entre 2 y 4");
                return "plan/crearPlan";
            }else{
                AnioAcademico anioAcademico=new AnioAcademico();
                anioAcademico.setId(anio);
                if(!anioAcademicoRepository.findById(anio).isPresent()){
                    anioAcademicoRepository.save(anioAcademico);
                    for(int i=0;horas.length>i;i++){
                        PlanEstudios planEstudios=new PlanEstudios();
                        planEstudios.setAnioAcademico(anioAcademico);
                        planEstudios.setAsignatura(asignaturas.get(i));
                        planEstudios.setHorasSemanales(horas[i]);
                        planEstudiosRepository.save(planEstudios);
                    }
                    return "redirect:/plan/listar";
                }else{
                    model.put("asignaturas", asignaturas);
                    model.put("anio",anio);
                    model.put("horas", horas);
                    model.put("alertaCampos", "EL plan de estudios actual ya existe");
                    return "plan/crearPlan";
                }
            }
            
        } else {
            return "redirect:/acceder";
        }
    }

    @GetMapping("/ver/{id}")
    public String ver(HttpSession session, @PathVariable String id, Map<String, Object> model) {
        if (session.getAttribute("usuario") != null) {
            AnioAcademico anioAcademico = anioAcademicoRepository.findById(id).orElse(null);
            if (anioAcademico != null) {
                List<PlanEstudios> anioPlanes = planEstudiosRepository.findByAnioAcademico(anioAcademico);
                model.put("anioAcademico", anioAcademico);
                model.put("anioPlanes",anioPlanes);
                return "plan/verPlan";
            } else {
                return "redirect:/plan/listar";
            }
        } else {
            return "redirect:/acceder";
        }
    }

}
