/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vacantespp.model;

public class Area {

    private String are_cCodigo;
    private String are_vcNombre;

    // Constructor
    public Area(String are_cCodigo, String are_vcNombre) {
        this.are_cCodigo = are_cCodigo;
        this.are_vcNombre = are_vcNombre;
    }

    // Getters and Setters
    public String getAre_cCodigo() {
        return are_cCodigo;
    }

    public void setAre_cCodigo(String are_cCodigo) {
        this.are_cCodigo = are_cCodigo;
    }

    public String getAre_vcNombre() {
        return are_vcNombre;
    }

    public void setAre_vcNombre(String are_vcNombre) {
        this.are_vcNombre = are_vcNombre;
    }
}
