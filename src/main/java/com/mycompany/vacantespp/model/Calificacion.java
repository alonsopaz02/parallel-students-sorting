/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vacantespp.model;

public class Calificacion {

    private int cal_iID;
    private int ide_iIndice;
    private String cod_vcCodigo;
    private double cal_fNotaHabilidades;
    private double cal_fNotaConocimientos;
    private double cal_fNotaFinal;
    private Integer cal_iMeritoEap;
    private Integer cal_iIngreso;
    private Integer cal_iMeritoGeneral;

    public Calificacion(int cal_iID, int ide_iIndice, String cod_vcCodigo, double cal_fNotaHabilidades, double cal_fNotaConocimientos, double cal_fNotaFinal, Integer cal_iMeritoEap, Integer cal_iIngreso, Integer cal_iMeritoGeneral) {
        this.cal_iID = cal_iID;
        this.ide_iIndice = ide_iIndice;
        this.cod_vcCodigo = cod_vcCodigo;
        this.cal_fNotaHabilidades = cal_fNotaHabilidades;
        this.cal_fNotaConocimientos = cal_fNotaConocimientos;
        this.cal_fNotaFinal = cal_fNotaFinal;
        this.cal_iMeritoEap = cal_iMeritoEap;
        this.cal_iIngreso = cal_iIngreso;
        this.cal_iMeritoGeneral = cal_iMeritoGeneral;
    }

    public int getCal_iID() {
        return cal_iID;
    }

    public void setCal_iID(int cal_iID) {
        this.cal_iID = cal_iID;
    }

    public int getIde_iIndice() {
        return ide_iIndice;
    }

    public void setIde_iIndice(int ide_iIndice) {
        this.ide_iIndice = ide_iIndice;
    }

    public String getCod_vcCodigo() {
        return cod_vcCodigo;
    }

    public void setCod_vcCodigo(String cod_vcCodigo) {
        this.cod_vcCodigo = cod_vcCodigo;
    }

    public double getCal_fNotaHabilidades() {
        return cal_fNotaHabilidades;
    }

    public void setCal_fNotaHabilidades(double cal_fNotaHabilidades) {
        this.cal_fNotaHabilidades = cal_fNotaHabilidades;
    }

    public double getCal_fNotaConocimientos() {
        return cal_fNotaConocimientos;
    }

    public void setCal_fNotaConocimientos(double cal_fNotaConocimientos) {
        this.cal_fNotaConocimientos = cal_fNotaConocimientos;
    }

    public double getCal_fNotaFinal() {
        return cal_fNotaFinal;
    }

    public void setCal_fNotaFinal(double cal_fNotaFinal) {
        this.cal_fNotaFinal = cal_fNotaFinal;
    }

    public Integer getCal_iMeritoEap() {
        return cal_iMeritoEap;
    }

    public void setCal_iMeritoEap(Integer cal_iMeritoEap) {
        this.cal_iMeritoEap = cal_iMeritoEap;
    }

    public Integer getCal_iIngreso() {
        return cal_iIngreso;
    }

    public void setCal_iIngreso(Integer cal_iIngreso) {
        this.cal_iIngreso = cal_iIngreso;
    }

    public Integer getCal_iMeritoGeneral() {
        return cal_iMeritoGeneral;
    }

    public void setCal_iMeritoGeneral(Integer cal_iMeritoGeneral) {
        this.cal_iMeritoGeneral = cal_iMeritoGeneral;
    }
}
