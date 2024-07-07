/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vacantespp.model;

public class Postulante {

    private String cod_vcCodigo;
    private String esc_vcCodigo;

    public Postulante(String cod_vcCodigo, String esc_vcCodigo) {
        this.cod_vcCodigo = cod_vcCodigo;
        this.esc_vcCodigo = esc_vcCodigo;
    }

    // Getters y Setters
    public String getCod_vcCodigo() {
        return cod_vcCodigo;
    }

    public void setCod_vcCodigo(String cod_vcCodigo) {
        this.cod_vcCodigo = cod_vcCodigo;
    }

    public String getEsc_vcCodigo() {
        return esc_vcCodigo;
    }

    public void setEsc_vcCodigo(String esc_vcCodigo) {
        this.esc_vcCodigo = esc_vcCodigo;
    }
}
