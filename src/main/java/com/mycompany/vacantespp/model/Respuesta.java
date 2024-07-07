/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vacantespp.model;

/**
 *
 * @author alons
 */
public class Respuesta {

    private int ide_iIndice;
    private String res_vcRespuesta;

    // Constructor
    public Respuesta(int ide_iIndice, String res_vcRespuesta) {
        this.ide_iIndice = ide_iIndice;
        this.res_vcRespuesta = res_vcRespuesta;
    }

    // Getters and Setters
    public int getIde_iIndice() {
        return ide_iIndice;
    }

    public void setIde_iIndice(int ide_iIndice) {
        this.ide_iIndice = ide_iIndice;
    }

    public String getRes_vcRespuesta() {
        return res_vcRespuesta;
    }

    public void setRes_vcRespuesta(String res_vcRespuesta) {
        this.res_vcRespuesta = res_vcRespuesta;
    }
}
