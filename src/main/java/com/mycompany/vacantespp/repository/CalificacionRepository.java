/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vacantespp.repository;

import com.mycompany.vacantespp.model.Calificacion;
import com.mycompany.vacantespp.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/* REPOSITORIO ENCARGADO DE LA CONEXION A BASE DE DATOS REFERENTE A LAS CALIFICACIONES Y DERIVADOS */
// Todas las operaciones del repositorio siguen una estructura basica de conexion y desconexion a SQL
public class CalificacionRepository {

    // Devuelve todas las calificaciones segun el codigo de escuela que se le indica
    public List<Calificacion> findCalificationsByEAP(String esc_vcCodigo) {
        List<Calificacion> calificaciones = new ArrayList<>();
        String sql = "SELECT * FROM calificacion "
                + "WHERE cod_vcCodigo "
                + "IN (SELECT cod_vcCodigo FROM postulante WHERE esc_vcCodigo = ?)";

        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, esc_vcCodigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Calificacion calificacion = new Calificacion(
                        resultSet.getInt("cal_iID"),
                        resultSet.getInt("ide_iIndice"),
                        resultSet.getString("cod_vcCodigo"),
                        resultSet.getDouble("cal_fNotaHabilidades"),
                        resultSet.getDouble("cal_fNotaConocimientos"),
                        resultSet.getDouble("cal_fNotaFinal"),
                        resultSet.getInt("cal_iMeritoEap"),
                        resultSet.getInt("cal_iIngreso"),
                        resultSet.getInt("cal_iMeritoGeneral")
                );
                calificaciones.add(calificacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return calificaciones;
    }

    // Encuentra todos los codigos de las escuelas, solo aquellas escuelas que tienen postulantes asociados
    public List<String> findAllEAPCodes() {
        List<String> schoolCodes = new ArrayList<>();
        String sql = "SELECT DISTINCT esc_vcCodigo FROM postulante";

        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String schoolCode = resultSet.getString("esc_vcCodigo");
                schoolCodes.add(schoolCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schoolCodes;
    }

    // Guarda las calificaciones, solo guarda las columnas de condicion de ingreso y orden de merit
    public void saveAll(List<Calificacion> calificaciones) {
        String sql = "UPDATE calificacion SET cal_iIngreso = ?, cal_iMeritoEap = ? WHERE cal_iID = ?";

        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            for (Calificacion calificacion : calificaciones) {
                preparedStatement.setInt(1, calificacion.getCal_iIngreso());
                preparedStatement.setInt(2, calificacion.getCal_iMeritoEap());
                preparedStatement.setInt(3, calificacion.getCal_iID());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Encuentra todas las calificaciones segun escuela y ordenadas por orden de merito
    public List<Calificacion> findCalificationsByEAPOrderedByMerit(String escuelaCodigo) {
        List<Calificacion> calificaciones = new ArrayList<>();
        String sql = "SELECT * FROM calificacion "
                + "WHERE cod_vcCodigo IN (SELECT cod_vcCodigo FROM postulante WHERE esc_vcCodigo = ?) "
                + "ORDER BY cal_iMeritoEap ASC";

        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, escuelaCodigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Calificacion calificacion = new Calificacion(
                        resultSet.getInt("cal_iID"),
                        resultSet.getInt("ide_iIndice"),
                        resultSet.getString("cod_vcCodigo"),
                        resultSet.getDouble("cal_fNotaHabilidades"),
                        resultSet.getDouble("cal_fNotaConocimientos"),
                        resultSet.getDouble("cal_fNotaFinal"),
                        resultSet.getInt("cal_iMeritoEap"),
                        resultSet.getInt("cal_iIngreso"),
                        resultSet.getInt("cal_iMeritoGeneral")
                );
                calificaciones.add(calificacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return calificaciones;
    }

    // Elimina todas las calificaciones sin codigo de identificacion o sin codigo de escuela
    public void deleteNullCalificaciones() {
        String sql = "DELETE FROM calificacion WHERE ide_iIndice IS NULL OR cod_vcCodigo IS NULL";

        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Actualizamos toda la tabla de calificaciones para rellenar el campo del codigo de la escuela a la que va cada postulante con joins
    public void updateCalificacionesWithCodigo() {
        String sql = "UPDATE calificacion c "
                + "JOIN identificacion i ON c.ide_iIndice = i.ide_iIndice "
                + "JOIN postulante p ON i.cod_vcCodigo = p.cod_vcCodigo "
                + "SET c.cod_vcCodigo = p.cod_vcCodigo "
                + "WHERE c.cal_iID > 0";

        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
