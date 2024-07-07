package com.mycompany.vacantespp.service;

/* Imports */
import com.mycompany.vacantespp.model.Calificacion;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/* Clase para hacer el ordenamiento de las calificaciones utilizando MergeSort */
public class ParallelMergeSort extends RecursiveTask<List<Calificacion>> {

    /*Atributos*/
    private List<Calificacion> calificaciones;

    /*Constructor*/
    public ParallelMergeSort(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /*Tarea que realizaran los hilos lanzados en paralelo*/
    @Override
    protected List<Calificacion> compute() {

        if (calificaciones.size() <= 1) {
            return calificaciones;
        }

        int mid = calificaciones.size() / 2;
        ParallelMergeSort leftTask = new ParallelMergeSort(calificaciones.subList(0, mid));
        ParallelMergeSort rightTask = new ParallelMergeSort(calificaciones.subList(mid, calificaciones.size()));

        // Fork para lanzar las tareas en paralelo
        leftTask.fork();
        rightTask.fork();

        // Join para obtener los resultados
        List<Calificacion> leftResult = leftTask.join();
        List<Calificacion> rightResult = rightTask.join();

        // Funcion Merge para continuar con el ordenamiento de manera recursiva
        return merge(leftResult, rightResult);
    }

    //Merge del MergeSort utilizado
    private List<Calificacion> merge(List<Calificacion> left, List<Calificacion> right) {
        List<Calificacion> merged = new ArrayList<>();
        int leftIndex = 0, rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            Calificacion leftCal = left.get(leftIndex);
            Calificacion rightCal = right.get(rightIndex);

            //Criterio de comparacion
            int compare = Comparator.comparing(Calificacion::getCal_fNotaFinal)
                    .thenComparing(Calificacion::getCal_fNotaConocimientos)
                    .thenComparing(Calificacion::getCal_fNotaHabilidades)
                    .thenComparing(Calificacion::getCod_vcCodigo)
                    .compare(leftCal, rightCal);

            if (compare <= 0) {
                merged.add(leftCal);
                leftIndex++;
            } else {
                merged.add(rightCal);
                rightIndex++;
            }
        }

        while (leftIndex < left.size()) {
            merged.add(left.get(leftIndex++));
        }

        while (rightIndex < right.size()) {
            merged.add(right.get(rightIndex++));
        }

        return merged;
    }
}
