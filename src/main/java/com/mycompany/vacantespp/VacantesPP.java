/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.vacantespp;

import com.mycompany.vacantespp.repository.CalificacionRepository;
import com.mycompany.vacantespp.service.ScoreService;
import com.mycompany.vacantespp.service.SortingService;
import com.mycompany.vacantespp.service.VacanteAssignmentService;

/**
 * Tarea grupal
 * 
 * Grupo de trabajo 10
 * - Dioses Martinez Maria Alejandra
 * - Paz Guillen Jose Alonso
 * - Alvarado Torres Alexandra Luisa
 * - Pereyra Exebio Dylan Patrick
 * - Sanchez Mirones Mario Eduardo
 * 
 */

public class VacantesPP {

    public static void main(String[] args) {
        CalificacionRepository calificacionRepository = new CalificacionRepository();
        SortingService sortingService = new SortingService();
        VacanteAssignmentService vacanteService = new VacanteAssignmentService();
        ScoreService scoreService = new ScoreService();
        
        // Estas funciones se utilizan para limpiar los datos presentes en la base de datos
        // Todas las calificaciones presentes en la base de datos no tienen asociado el codigo del postulante
        // Las calificaciones carecen del codigo del postulante pero esta se puede obtener con un Join, utilizando la columna de identificacion
        calificacionRepository.updateCalificacionesWithCodigo();
        
        // Se eliminan aquellas calificaciones nulas las cuales no tienen asociada una identificacion ni un codigo, por lo que
        // no es posible asociarlas con una escuela, ademas de que no tienen nota
        calificacionRepository.deleteNullCalificaciones();

        // Ordenar y asignar méritos en paralelo
        // Tarea en paralelo usando ForkJoin que ordena y asigna el orden de merito correspondiente segun cada escuela diferente
        sortingService.parallelSortAndAssignMeritByEAP();

        // Asignar vacantes en paralelo
        // Tarea en paralelo segun la cantidad de escuelas, cada tarea asigna la condicion de ingreso de la escuela que le corresponde
        vacanteService.assignVacantesByEAP();
        
        // Hallar los puntajes minimos y maximos por escuela
        // Se llena la tabla con puntajes minimos y maximos correspondientes, tambien se lanza tarea paralela segun cada escuela
        scoreService.calculateAndSaveMinMaxScores();

        System.out.println("Calificaciones actualizadas, vacantes asignadas y puntajes mínimos y máximos guardados en la base de datos.");
    }
}
