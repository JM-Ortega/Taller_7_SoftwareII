package co.edu.unicauca.builder.builder;

import co.edu.unicauca.builder.domain.Project;
import co.edu.unicauca.builder.domain.ValidationException;

public abstract class ProjectBuilder {

    protected Project project;

    public void createNewProject() {
        project = new Project();
    }

    public Project getProject() {
        validateCommon();
        validateStudentsRule();
        return project;
    }

    public abstract void buildTitulo(String titulo);

    public abstract void buildModalidad();

    public abstract void buildEstudiante1(String estudiante);

    public abstract void buildEstudiante2(String estudiante);

    public abstract void buildDirector(String director);

    public abstract void buildCodirector1(String codirector1);

    public abstract void buildCodirector2(String codirector2);

    public abstract void buildObjetivoGeneral(String objetivo);


    public abstract void buildObjetivoEspecifico(String objetivo);


    protected void validateCommon() {
        if (blank(project.getDirector())) {
            throw new ValidationException("Director es obligatorio");
        }
    }

    protected abstract void validateStudentsRule();

    protected void addObjetivo(String texto) {
        if (project.getObjetivosEspecificos().size() >= 4) {
            throw new ValidationException("Máximo 4 objetivos específicos");
        }
        project.getObjetivosEspecificos().add(norm(texto));
    }

    protected String norm(String s) {
        return s == null ? null : s.trim();
    }

    protected String optional(String s) {
        return blank(s) ? null : s;
    }

    protected boolean blank(String s) {
        return s == null || s.isBlank();
    }
}