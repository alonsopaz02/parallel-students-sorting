/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vacantespp.model;

public class Vacante {

    private String esc_vcCodigo;
    private int vac_iVacantes;
    private float vac_iPuntajeMinimo;
    private float vac_iPuntajeMaximo;
    private int vac_iIngresantes; 

    public Vacante(String esc_vcCodigo, int vac_iVacantes, float vac_iPuntajeMinimo, float vac_iPuntajeMaximo, int vac_iIngresantes) {
        this.esc_vcCodigo = esc_vcCodigo;
        this.vac_iVacantes = vac_iVacantes;
        this.vac_iPuntajeMinimo = vac_iPuntajeMinimo;
        this.vac_iPuntajeMaximo = vac_iPuntajeMaximo;
        this.vac_iIngresantes = vac_iIngresantes;
    }

    public String getEsc_vcCodigo() {
        return esc_vcCodigo;
    }

    public void setEsc_vcCodigo(String esc_vcCodigo) {
        this.esc_vcCodigo = esc_vcCodigo;
    }

    public int getVac_iVacantes() {
        return vac_iVacantes;
    }

    public void setVac_iVacantes(int vac_iVacantes) {
        this.vac_iVacantes = vac_iVacantes;
    }

    public float getVac_iPuntajeMinimo() {
        return vac_iPuntajeMinimo;
    }

    public void setVac_iPuntajeMinimo(float vac_iPuntajeMinimo) {
        this.vac_iPuntajeMinimo = vac_iPuntajeMinimo;
    }

    public float getVac_iPuntajeMaximo() {
        return vac_iPuntajeMaximo;
    }

    public void setVac_iPuntajeMaximo(float vac_iPuntajeMaximo) {
        this.vac_iPuntajeMaximo = vac_iPuntajeMaximo;
    }

    public int getVac_iIngresantes() {
        return vac_iIngresantes;
    }

    public void setVac_iIngresantes(int vac_iIngresantes) {
        this.vac_iIngresantes = vac_iIngresantes;
    }

}
