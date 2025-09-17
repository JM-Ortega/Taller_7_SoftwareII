package co.edu.unicauca.builder.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private final List<String> objetivosEspecificos = new ArrayList<>();
    private String titulo;
    private Modalidad modalidad;
    private String estudiante1;
    private String estudiante2;
    private LocalDate fechaCreacion = LocalDate.now();
    private String director;
    private String codirector1;
    private String codirector2;
    private String objetivoGeneral;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public String getEstudiante1() {
        return estudiante1;
    }

    public void setEstudiante1(String estudiante1) {
        this.estudiante1 = estudiante1;
    }

    public String getEstudiante2() {
        return estudiante2;
    }

    public void setEstudiante2(String estudiante2) {
        this.estudiante2 = estudiante2;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCodirector1() {
        return codirector1;
    }

    public void setCodirector1(String codirector1) {
        this.codirector1 = codirector1;
    }

    public String getCodirector2() {
        return codirector2;
    }

    public void setCodirector2(String codirector2) {
        this.codirector2 = codirector2;
    }

    public String getObjetivoGeneral() {
        return objetivoGeneral;
    }

    public void setObjetivoGeneral(String objetivoGeneral) {
        this.objetivoGeneral = objetivoGeneral;
    }

    public List<String> getObjetivosEspecificos() {
        return objetivosEspecificos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Project {")
                .append("\n  titulo='").append(titulo).append('\'')
                .append(",\n  modalidad=").append(modalidad)
                .append(",\n  estudiante1='").append(estudiante1).append('\'')
                .append(",\n  estudiante2='").append(estudiante2).append('\'')
                .append(",\n  fechaCreacion=").append(fechaCreacion)
                .append(",\n  director='").append(director).append('\'')
                .append(",\n  codirector1='").append(codirector1).append('\'')
                .append(",\n  codirector2='").append(codirector2).append('\'')
                .append(",\n  objetivoGeneral='").append(objetivoGeneral).append('\'')
                .append(",\n  objetivosEspecificos=").append(objetivosEspecificos)
                .append("\n}");
        return sb.toString();
    }

}