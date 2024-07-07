package com.mycompany.vacantespp.service;

import com.mycompany.vacantespp.model.Calificacion;
import com.mycompany.vacantespp.repository.CalificacionRepository;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/* Servicio que usara la clase de ParallelMergeSort */
// Este servicio para ordenar manejara hilos con ForkJoin para paralelizar el ordenamiento de cada escuela
// La ordenacion, mas a alla de estar separada por escuelas, tambien tiene paralelizacion al momento de ordenar
public class SortingService {

    private ForkJoinPool forkJoinPool;
    private CalificacionRepository calificacionRepository;

    public SortingService() {

        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processors (cores): " + availableProcessors);

        this.forkJoinPool = new ForkJoinPool();
        this.calificacionRepository = new CalificacionRepository();
    }

    // Metodo que gestiona para ordenar y asignar merito
    public void parallelSortAndAssignMeritByEAP() {
        // Obtener todas las escuelas únicas desde la base de datos
        List<String> escuelas = calificacionRepository.findAllEAPCodes();

        // Para cada escuela, iniciar una tarea de Fork/Join para ordenar y asignar mérito
        for (String escuela : escuelas) {
            forkJoinPool.invoke(new SortAndAssignMeritTask(escuela));
        }
    }

    // Clase que representa la tarea o Task para ordenar y asignar merito
    private class SortAndAssignMeritTask extends RecursiveTask<Void> {

        private String escuela;

        public SortAndAssignMeritTask(String escuela) {
            this.escuela = escuela;
        }

        @Override
        protected Void compute() {

            // Obtener calificaciones para la escuela actual usando el método existente en el repositorio
            List<Calificacion> calificaciones = calificacionRepository.findCalificationsByEAP(escuela);

            // Ordenar las calificaciones usando ParallelMergeSort
            ParallelMergeSort parallelMergeSort = new ParallelMergeSort(calificaciones);
            List<Calificacion> sortedCalificaciones = forkJoinPool.invoke(parallelMergeSort);
            printActiveThreads("Hilos ordenando en paralelo");

            // Asignar orden de mérito (cal_iMeritoEap)
            assignMerit(sortedCalificaciones);

            // Actualizar la base de datos con los resultados ordenados y el orden de mérito asignado
            calificacionRepository.saveAll(sortedCalificaciones);

            return null;
        }

        
        // AUXILIARES
        private void assignMerit(List<Calificacion> calificaciones) {
            int merito = 1;
            int size = calificaciones.size();
            for (int i = 0; i < size; i++) {
                calificaciones.get(size - 1 - i).setCal_iMeritoEap(merito++);
            }
        }

        // Ver informacion de los hilos
        private void printActiveThreads(String stage) {
            int activeThreads = forkJoinPool.getActiveThreadCount();
            int poolSize = forkJoinPool.getPoolSize();
            int parallelism = ForkJoinPool.commonPool().getParallelism();

            System.out.println("[" + stage + "] Active threads: " + activeThreads
                    + ", \nPool size: " + poolSize + "\nParalelismo: "+parallelism);
        }
    }
}
