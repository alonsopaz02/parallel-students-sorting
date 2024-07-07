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

// Servicio para hallar los puntajes minimos y maximos de cada EAP
public class ScoreService {

    private ForkJoinPool forkJoinPool;
    private CalificacionRepository calificacionRepository;
    private VacanteRepository vacanteRepository;

    public ScoreService() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processors (cores): " + availableProcessors);

        this.forkJoinPool = new ForkJoinPool(availableProcessors);
        this.calificacionRepository = new CalificacionRepository();
        this.vacanteRepository = new VacanteRepository();
    }

    public void calculateAndSaveMinMaxScores() {
        // Obtenemos todos los codigos de las EAP
        List<String> escuelas = calificacionRepository.findAllEAPCodes();

        // Para cada codigo existe una tarea que calcule el minimo o maximo
        for (String escuela : escuelas) {
            forkJoinPool.invoke(new CalculateMinMaxScoresAction(escuela));
        }

        printActiveThreads("Hilos de puntaje minimo y maximo");
    }

    private void printActiveThreads(String stage) {
        int poolSize = forkJoinPool.getPoolSize();
        int parallelism = ForkJoinPool.commonPool().getParallelism();

        System.out.println("[" + stage + "]" + ", \nPool size: " + poolSize + "\nParalelismo: " + parallelism);
    }

    // Accion recursiva para calcular los puntajes
    public class CalculateMinMaxScoresAction extends RecursiveAction {

        private String escuela;

        public CalculateMinMaxScoresAction(String escuela) {
            this.escuela = escuela;
        }

        @Override
        protected void compute() {
            // Obtenemos las calificaciones ordenadas por orden de merito (ya que ya tenemos los ordenes de merito, podemos realizar esta accion)
            List<Calificacion> calificaciones = calificacionRepository.findCalificationsByEAPOrderedByMerit(escuela);
            double puntajeMinimo = Double.MAX_VALUE;
            double puntajeMaximo = Double.MIN_VALUE;

            // Logica para hallar el minimo y maximo 
            for (Calificacion calificacion : calificaciones) {
                if (calificacion.getCal_iIngreso() == 1) {
                    puntajeMinimo = Math.min(puntajeMinimo, calificacion.getCal_fNotaFinal());
                    puntajeMaximo = Math.max(puntajeMaximo, calificacion.getCal_fNotaFinal());
                }
            }

            // Se actualiza en la tabla correspondiente
            vacanteRepository.updatePuntajeMinimoYMaximo(escuela, puntajeMinimo, puntajeMaximo);
        }

    }
}
