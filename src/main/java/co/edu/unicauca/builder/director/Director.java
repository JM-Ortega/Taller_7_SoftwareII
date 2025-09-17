package co.edu.unicauca.builder.director;

import co.edu.unicauca.builder.builder.ProjectBuilder;
import co.edu.unicauca.builder.domain.Modalidad;
import co.edu.unicauca.builder.domain.Project;

import java.util.List;

public class Director {

    private ProjectBuilder projectBuilder;

    public void setProjectBuilder(ProjectBuilder projectBuilder) {
        this.projectBuilder = projectBuilder;
    }

    public Project getProject() {
        return projectBuilder.getProject();
    }

    public void buildProject() {
        projectBuilder.createNewProject();
        projectBuilder.buildModalidad();
        projectBuilder.buildTitulo("Proyecto demo");
        projectBuilder.buildDirector("Dra. Pérez");
        projectBuilder.buildEstudiante1("Estudiante Uno");
        projectBuilder.buildObjetivoGeneral("Objetivo general de ejemplo");
        projectBuilder.buildObjetivoEspecifico("Objetivo 1");
        projectBuilder.buildObjetivoEspecifico("Objetivo 2");
    }

    public Project buildProject(String titulo,
                                String director,
                                String estudiante1,
                                String estudiante2,
                                String objetivoGeneral,
                                List<String> objetivosEspecificos,
                                String codirector1,
                                String codirector2) {
        projectBuilder.createNewProject();
        projectBuilder.buildModalidad();
        projectBuilder.buildTitulo(titulo);
        projectBuilder.buildDirector(director);
        projectBuilder.buildEstudiante1(estudiante1);
        projectBuilder.buildEstudiante2(estudiante2);
        projectBuilder.buildCodirector1(codirector1);
        projectBuilder.buildCodirector2(codirector2);
        projectBuilder.buildObjetivoGeneral(objetivoGeneral);
        addObjetivos(objetivosEspecificos);
        return projectBuilder.getProject();
    }

    public Project constructInvestigacion(ProjectBuilder builder) {
        builder.createNewProject();
        builder.buildModalidad();
        builder.buildTitulo("Proyecto de investigación");
        builder.buildDirector("Dra. Pérez");
        builder.buildEstudiante1("Estudiante 1");
        builder.buildEstudiante2("Estudiante 2");
        builder.buildObjetivoGeneral("Objetivo general investigación");
        builder.buildObjetivoEspecifico("Objetivo específico 1");
        builder.buildObjetivoEspecifico("Objetivo específico 2");
        return builder.getProject();
    }

    public Project constructPractica(ProjectBuilder builder) {
        builder.createNewProject();
        builder.buildModalidad();
        builder.buildTitulo("Proyecto de práctica profesional");
        builder.buildDirector("Ing. Ramírez");
        builder.buildEstudiante1("Estudiante único");
        builder.buildObjetivoGeneral("Objetivo general práctica");
        builder.buildObjetivoEspecifico("Objetivo específico 1");
        return builder.getProject();
    }

    public Project constructCoterminal(ProjectBuilder builder) {
        builder.createNewProject();
        builder.buildModalidad();
        builder.buildTitulo("Proyecto plan coterminal");
        builder.buildDirector("Dr. López");
        builder.buildEstudiante1("Estudiante único");
        builder.buildObjetivoGeneral("Objetivo general coterminal");
        builder.buildObjetivoEspecifico("Objetivo específico 1");
        builder.buildObjetivoEspecifico("Objetivo específico 2");
        builder.buildObjetivoEspecifico("Objetivo específico 3");
        return builder.getProject();
    }

    private void addObjetivos(List<String> objetivos) {
        if (objetivos == null) return;
        for (String obj : objetivos) {
            projectBuilder.buildObjetivoEspecifico(obj);
        }
    }
}
