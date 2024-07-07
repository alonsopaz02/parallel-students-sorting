/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vacantespp.model;

public class Escuela {

    private String esc_vcCodigo;
    private String esc_vcNombre;
    private String are_cCodigo;

    public Escuela(String esc_vcCodigo, String esc_vcNombre, String are_cCodigo) {
        this.esc_vcCodigo = esc_vcCodigo;
        this.esc_vcNombre = esc_vcNombre;
        this.are_cCodigo = are_cCodigo;
    }

    public String getEsc_vcCodigo() {
        return esc_vcCodigo;
    }

    public void setEsc_vcCodigo(String esc_vcCodigo) {
        this.esc_vcCodigo = esc_vcCodigo;
    }

    public String getEsc_vcNombre() {
        return esc_vcNombre;
    }

    public void setEsc_vcNombre(String esc_vcNombre) {
        this.esc_vcNombre = esc_vcNombre;
    }

    public String getAre_cCodigo() {
        return are_cCodigo;
    }

    public void setAre_cCodigo(String are_cCodigo) {
        this.are_cCodigo = are_cCodigo;
    }

}
