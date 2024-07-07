/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vacantespp.service;

import com.mycompany.vacantespp.model.Calificacion;
import com.mycompany.vacantespp.repository.CalificacionRepository;
import com.mycompany.vacantespp.repository.VacanteRepository;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class VacanteAssignmentService {

    private ForkJoinPool forkJoinPool;
    private CalificacionRepository calificacionRepository;
    private VacanteRepository vacanteRepository;

    public VacanteAssignmentService() {
        // La PC con la que el grupo ejecutó el codigo solo tiene 4 nucleos disponible
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processors (cores): " + availableProcessors);

        this.forkJoinPool = new ForkJoinPool(availableProcessors);
        this.calificacionRepository = new CalificacionRepository();
        this.vacanteRepository = new VacanteRepository();
    }

    // Inicializacion de las tareas
    public void assignVacantesByEAP() {
        List<String> escuelas = calificacionRepository.findAllEAPCodes();

        // Se crea e invoca una tarea para cada escuela que exista
        for (String escuela : escuelas) {
            forkJoinPool.invoke(new AssignVacantesAction(escuela));
        }
        
        printActiveThreads("Hilos de asignacion de vacantes");
    }

    // Imprimir informacion de hilos, getParallelism sacado del ejmplo en clase que hicimos con ForkJoin
    private void printActiveThreads(String stage) {
        int poolSize = forkJoinPool.getPoolSize();
        int parallelism = ForkJoinPool.commonPool().getParallelism();

        System.out.println("[" + stage + "]" + ", \nPool size: " + poolSize + "\nParalelismo: " + parallelism);
    }

    // Tarea o accion que asignara vacantes para cada
    private class AssignVacantesAction extends RecursiveAction {

        private String escuela;

        public AssignVacantesAction(String escuela) {
            this.escuela = escuela;
        }

        @Override
        protected void compute() {
            List<Calificacion> calificaciones = calificacionRepository.findCalificationsByEAPOrderedByMerit(escuela);

            int vacantesDisponibles = vacanteRepository.getVacantesByEscuela(escuela);
            int ingresantes = 0;

            // Iterar sobre las calificaciones para asignar vacantes
            for (int i = 0; i < calificaciones.size(); i++) {
                if (vacantesDisponibles > 0) {
                    Calificacion calificacion = calificaciones.get(i);
                    calificacion.setCal_iIngreso(1); // Asignar ingreso
                    ingresantes++;

                    // Verificar empate en la última posición con vacante disponible. Todos los empatados al final ingresaran
                    if (i == calificaciones.size() - 1 || calificacion.getCal_fNotaFinal() != calificaciones.get(i + 1).getCal_fNotaFinal()) {
                        vacantesDisponibles--;
                    }
                } else {
                    calificaciones.get(i).setCal_iIngreso(0); // No hay vacantes disponibles
                }
            }

            // Guardamos en la base de datos
            calificacionRepository.saveAll(calificaciones);

            // Guardamos tambien la cantidad de vacantes que se ocuparon
            vacanteRepository.updateVacanciesCovered(escuela, ingresantes);

        }
    }

}
