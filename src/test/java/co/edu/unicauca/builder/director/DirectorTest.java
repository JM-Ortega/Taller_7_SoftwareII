package co.edu.unicauca.builder.director;

import co.edu.unicauca.builder.builder.impl.CoterminalPlanProjectBuilder;
import co.edu.unicauca.builder.builder.impl.ProfessionProjectBuilder;
import co.edu.unicauca.builder.builder.impl.ResearchProjectBuilder;
import co.edu.unicauca.builder.domain.Modalidad;
import co.edu.unicauca.builder.domain.Project;
import co.edu.unicauca.builder.domain.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DirectorTest {

    private Director director;

    @BeforeEach
    void setUp() {
        director = new Director();
    }

    // casos felices

    @Test
    void investigacionConUnEstudianteEsValida() {
        director.setProjectBuilder(new ResearchProjectBuilder());
        director.buildProject();

        Project p = director.getProject();
        assertNotNull(p);
        assertEquals(Modalidad.INVESTIGACION, p.getModalidad());
        assertNotNull(p.getEstudiante1());
        assertNull(p.getEstudiante2());
    }

    @Test
    void investigacionConDosEstudiantesEsValida() {
        ResearchProjectBuilder builder = new ResearchProjectBuilder();
        builder.createNewProject();
        builder.buildModalidad();
        builder.buildTitulo("proyecto con 2 estudiantes");
        builder.buildDirector("dr. smith");
        builder.buildEstudiante1("ana");
        builder.buildEstudiante2("juan");
        builder.buildObjetivoGeneral("objetivo general");
        builder.buildObjetivoEspecifico("objetivo 1");
        builder.buildObjetivoEspecifico("objetivo 2");

        Project p = builder.getProject();
        assertEquals(Modalidad.INVESTIGACION, p.getModalidad());
        assertEquals("ana", p.getEstudiante1());
        assertEquals("juan", p.getEstudiante2());
    }

    @Test
    void practicaProfesionalUnEstudianteEsValida() {
        director.setProjectBuilder(new ProfessionProjectBuilder());
        director.buildProject();

        Project p = director.getProject();
        assertEquals(Modalidad.PRACTICA_PROFESIONAL, p.getModalidad());
        assertNotNull(p.getEstudiante1());
        assertNull(p.getEstudiante2());
    }

    @Test
    void coterminalUnEstudianteEsValida() {
        director.setProjectBuilder(new CoterminalPlanProjectBuilder());
        director.buildProject();

        Project p = director.getProject();
        assertEquals(Modalidad.COTERMINAL, p.getModalidad());
        assertNotNull(p.getEstudiante1());
        assertNull(p.getEstudiante2());
    }

    // casos de error

    @Test
    void investigacionSinEstudiantesLanzaExcepcion() {
        ResearchProjectBuilder builder = new ResearchProjectBuilder();
        builder.createNewProject();
        builder.buildModalidad();
        builder.buildTitulo("proyecto invalido");
        builder.buildDirector("director");
        builder.buildObjetivoGeneral("objetivo general");

        assertThrows(ValidationException.class, builder::getProject);
    }

    @Test
    void practicaProfesionalConDosEstudiantesLanzaExcepcion() {
        ProfessionProjectBuilder builder = new ProfessionProjectBuilder();
        builder.createNewProject();
        builder.buildModalidad();
        builder.buildTitulo("proyecto invalido");
        builder.buildDirector("director");
        builder.buildEstudiante1("carlos");

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> builder.buildEstudiante2("pedro")
        );
        assertEquals("práctica profesional: solo 1 estudiante", ex.getMessage().toLowerCase());
    }

    @Test
    void masDeCuatroObjetivosLanzaExcepcion() {
        ResearchProjectBuilder builder = new ResearchProjectBuilder();
        builder.createNewProject();
        builder.buildModalidad();
        builder.buildTitulo("proyecto con muchos objetivos");
        builder.buildDirector("director");
        builder.buildEstudiante1("estudiante");
        builder.buildObjetivoGeneral("objetivo general");

        builder.buildObjetivoEspecifico("o1");
        builder.buildObjetivoEspecifico("o2");
        builder.buildObjetivoEspecifico("o3");
        builder.buildObjetivoEspecifico("o4");

        assertThrows(ValidationException.class, () -> builder.buildObjetivoEspecifico("o5"));
    }

    @Test
    void directorFaltanteLanzaExcepcion() {
        ResearchProjectBuilder builder = new ResearchProjectBuilder();
        builder.createNewProject();
        builder.buildModalidad();
        builder.buildTitulo("proyecto sin director");
        builder.buildEstudiante1("estudiante");
        builder.buildObjetivoGeneral("objetivo general");

        assertThrows(ValidationException.class, builder::getProject);
    }

    // tests adicionales para cubrir bordes

    @Test
    void cuatroObjetivosEsValido() {
        ResearchProjectBuilder b = new ResearchProjectBuilder();
        b.createNewProject();
        b.buildModalidad();
        b.buildTitulo("ok 4 obj");
        b.buildDirector("dir");
        b.buildEstudiante1("e1");
        b.buildObjetivoGeneral("og");
        b.buildObjetivoEspecifico("o1");
        b.buildObjetivoEspecifico("o2");
        b.buildObjetivoEspecifico("o3");
        b.buildObjetivoEspecifico("o4");
        assertDoesNotThrow(b::getProject);
    }

    @Test
    void practicaProfesionalConEstudiante2NullNoLanza() {
        ProfessionProjectBuilder b = new ProfessionProjectBuilder();
        director.setProjectBuilder(b);
        assertDoesNotThrow(() -> director.buildProject(
                "titulo", "dir", "e1", null, "og",
                List.of("o1", "o2"), null, null
        ));
    }

    @Test
    void codirectoresOpcionales() {
        ResearchProjectBuilder b = new ResearchProjectBuilder();
        b.createNewProject();
        b.buildModalidad();
        b.buildTitulo("codirectores");
        b.buildDirector("dir");
        b.buildEstudiante1("e1");
        b.buildObjetivoGeneral("og");

        assertDoesNotThrow(b::getProject);
        b.buildCodirector1("c1");
        assertDoesNotThrow(b::getProject);
        b.buildCodirector2("c2");
        assertDoesNotThrow(b::getProject);
    }

    @Test
    void faltaDirectorFallaIndependienteDelOrden() {
        ResearchProjectBuilder b = new ResearchProjectBuilder();
        b.createNewProject();
        b.buildModalidad();
        b.buildTitulo("sin director");
        b.buildEstudiante1("e1");
        b.buildObjetivoGeneral("og");
        assertThrows(ValidationException.class, b::getProject);
    }

    @Test
    void recetaDirectorUsaUnSoloEstudiante() {
        director.setProjectBuilder(new ProfessionProjectBuilder());
        director.buildProject();
        Project p = director.getProject();
        assertNotNull(p.getEstudiante1());
        assertNull(p.getEstudiante2());
    }
}
