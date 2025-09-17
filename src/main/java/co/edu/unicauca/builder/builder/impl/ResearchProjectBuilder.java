package co.edu.unicauca.builder.builder.impl;


import co.edu.unicauca.builder.builder.ProjectBuilder;
import co.edu.unicauca.builder.domain.Modalidad;
import co.edu.unicauca.builder.domain.ValidationException;


public class ResearchProjectBuilder extends ProjectBuilder {


    @Override
    public void buildTitulo(String titulo) {
        project.setTitulo(norm(titulo));
    }

    @Override
    public void buildModalidad() {
        project.setModalidad(Modalidad.INVESTIGACION);
    }

    @Override
    public void buildEstudiante1(String estudiante) {
        project.setEstudiante1(norm(estudiante));
    }

    @Override
    public void buildEstudiante2(String estudiante) {
        project.setEstudiante2(optional(norm(estudiante)));
    }

    @Override
    public void buildDirector(String director) {
        project.setDirector(norm(director));
    }

    @Override
    public void buildCodirector1(String codirector1) {
        project.setCodirector1(optional(norm(codirector1)));
    }

    @Override
    public void buildCodirector2(String codirector2) {
        project.setCodirector2(optional(norm(codirector2)));
    }

    @Override
    public void buildObjetivoGeneral(String objetivo) {
        project.setObjetivoGeneral(norm(objetivo));
    }

    @Override
    public void buildObjetivoEspecifico(String objetivo) {
        addObjetivo(objetivo);
    }

    @Override
    protected void validateStudentsRule() {
        int count = 0;
        if (!blank(project.getEstudiante1())) count++;
        if (!blank(project.getEstudiante2())) count++;
        if (count < 1) {
            throw new ValidationException("Investigación: requiere al menos 1 estudiante");
        }
    }
}
