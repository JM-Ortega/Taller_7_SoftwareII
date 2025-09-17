package co.edu.unicauca.builder.app;

import co.edu.unicauca.builder.builder.impl.CoterminalPlanProjectBuilder;
import co.edu.unicauca.builder.builder.impl.ProfessionProjectBuilder;
import co.edu.unicauca.builder.builder.impl.ResearchProjectBuilder;
import co.edu.unicauca.builder.director.Director;
import co.edu.unicauca.builder.domain.Project;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Director director = new Director();

        List<String> objetivosInvestigacion = Arrays.asList(
                "Analizar el estado del arte",
                "Disenar la arquitectura del sistema",
                "Implementar el prototipo",
                "Validar los resultados obtenidos"
        );

        List<String> objetivosPractica = Arrays.asList(
                "Analizar los requerimientos del sistema",
                "Disenar la arquitectura de software",
                "Implementar las funcionalidades principales"
        );

        List<String> objetivosCoterminal = Arrays.asList(
                "Identificar los cuellos de botella actuales",
                "Proponer mejoras en los procesos"
        );

        System.out.println("1. PROYECTO DE INVESTIGACION:");
        System.out.println("-----------------------------");

        director.setProjectBuilder(new ResearchProjectBuilder());
        try {
            Project investigacion = director.buildProject(
                    "Machine Learning aplicado a IoT para Smart Cities",
                    "Dr. Garcia Lopez",
                    "Juan Perez Martinez",
                    "Maria Gonzalez Silva",
                    "Implementar tecnicas de ML en dispositivos IoT para mejorar la gestion urbana",
                    objetivosInvestigacion,
                    "Dr. Martinez Torres",
                    null
            );
            System.out.println("Proyecto creado exitosamente:");
            System.out.println(investigacion);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\n" + "=".repeat(60) + "\n");

        System.out.println("2. PRACTICA PROFESIONAL:");
        System.out.println("------------------------");

        director.setProjectBuilder(new ProfessionProjectBuilder());
        try {
            Project practica = director.buildProject(
                    "Sistema de Gestion Integral para PYMES",
                    "Ing. Silva Rodriguez",
                    "Ana Lopez Herrera",
                    null,
                    "Desarrollar un sistema web para la gestion integral de PYMES",
                    objetivosPractica,
                    "Ing. Torres Vega",
                    null
            );
            System.out.println("Proyecto creado exitosamente:");
            System.out.println(practica);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\n" + "=".repeat(60) + "\n");

        System.out.println("3. PLAN COTERMINAL:");
        System.out.println("------------------");

        director.setProjectBuilder(new CoterminalPlanProjectBuilder());
        try {
            Project coterminal = director.buildProject(
                    "Optimizacion de Procesos Academicos mediante BI",
                    "Dra. Morales Castro",
                    "Carlos Ruiz Mendoza",
                    null,
                    "Mejorar la eficiencia de procesos academicos con BI",
                    objetivosCoterminal,
                    null,
                    null
            );
            System.out.println("Proyecto creado exitosamente:");
            System.out.println(coterminal);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }


        try {
            System.out.println(director.constructCoterminal(new CoterminalPlanProjectBuilder()));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        try {
            System.out.println(director.constructPractica(new ProfessionProjectBuilder()));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        try {
            System.out.println(director.constructInvestigacion(new ResearchProjectBuilder()));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\n" + "=".repeat(60) + "\n");

        System.out.println("4. PRUEBAS DE VALIDACION:");
        System.out.println("-------------------------");

        System.out.println("Probando: Practica profesional con 2 estudiantes (debe fallar)");
        director.setProjectBuilder(new ProfessionProjectBuilder());
        try {
            Project invalid = director.buildProject(
                    "Proyecto Invalido",
                    "Director Test",
                    "Estudiante 1",
                    "Estudiante 2",
                    "Objetivo invalido",
                    List.of("Objetivo 1"),
                    null,
                    null
            );
            System.out.println("ERROR: Deberia haber fallado!");
        } catch (Exception e) {
            System.out.println("Validacion exitosa: " + e.getMessage());
        }

        System.out.println("\nProbando: Proyecto sin director (debe fallar)");
        director.setProjectBuilder(new ResearchProjectBuilder());
        try {
            Project invalid = director.buildProject(
                    "Proyecto Sin Director",
                    null,
                    "Estudiante 1",
                    null,
                    "Objetivo test",
                    List.of("Objetivo 1"),
                    null,
                    null
            );
            System.out.println("ERROR: Deberia haber fallado!");
        } catch (Exception e) {
            System.out.println("Validacion exitosa: " + e.getMessage());
        }

        System.out.println("\nProbando: Proyecto con mas de 4 objetivos especificos (debe fallar)");
        try {
            List<String> muchos_objetivos = Arrays.asList(
                    "Objetivo 1", "Objetivo 2", "Objetivo 3", "Objetivo 4", "Objetivo 5"
            );
            Project invalid = director.buildProject(
                    "Proyecto Con Muchos Objetivos",
                    "Director Test",
                    "Estudiante 1",
                    null,
                    "Objetivo general",
                    muchos_objetivos,
                    null,
                    null
            );
            System.out.println("ERROR: Deberia haber fallado!");
        } catch (Exception e) {
            System.out.println("Validacion exitosa: " + e.getMessage());
        }

        System.out.println("\n" + "=".repeat(60) + "\n");
        System.out.println("5. PRUEBAS DE Dierector Construct:");
        System.out.println("-------------------------");


        try {
            System.out.println(director.constructInvestigacion(new CoterminalPlanProjectBuilder()));
            System.out.println("ERROR: Deberia haber fallado!");
        } catch (Exception e) {
            System.out.println("Validacion exitosa: " + e.getMessage());
        }

        try {
            System.out.println(director.constructInvestigacion(new ProfessionProjectBuilder()));
            System.out.println("ERROR: Deberia haber fallado!");
        } catch (Exception e) {
            System.out.println("Validacion exitosa: " + e.getMessage());
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("FIN DE LAS PRUEBAS");
        System.out.println("=".repeat(60));
    }
}
