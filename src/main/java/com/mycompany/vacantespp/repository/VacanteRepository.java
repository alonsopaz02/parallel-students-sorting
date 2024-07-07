/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vacantespp.repository;

import com.mycompany.vacantespp.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VacanteRepository {

    // Actualizamos puntajes minimos y maximos de la tabla de vacantes
    public void updatePuntajeMinimoYMaximo(String esc_vcCodigo, double puntajeMinimo, double puntajeMaximo) {
        String sql = "UPDATE vacante SET vac_iPuntajeMinimo = ?, vac_iPuntajeMaximo = ? WHERE esc_vcCodigo = ?";

        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setDouble(1, puntajeMinimo);
            preparedStatement.setDouble(2, puntajeMaximo);
            preparedStatement.setString(3, esc_vcCodigo);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Se obtiene la cantidad de vacantes que ofrece cada escuela
    public int getVacantesByEscuela(String esc_vcCodigo) {
        String sql = "SELECT vac_iVacantes FROM vacante WHERE esc_vcCodigo = ?";
        int vacantes = 0;

        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, esc_vcCodigo);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                vacantes = resultSet.getInt("vac_iVacantes");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vacantes;
    }

    // Se actualiza la cantidad de ingresantes, de vacantes cubiertas para cada escuela
    public void updateVacanciesCovered(String esc_vcCodigo, int ingresantes) {
        String sql = "UPDATE vacante SET vac_iIngresantes = ? WHERE esc_vcCodigo = ?";

        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, ingresantes);
            preparedStatement.setString(2, esc_vcCodigo);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
