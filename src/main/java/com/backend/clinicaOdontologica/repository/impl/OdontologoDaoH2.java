package com.backend.clinicaOdontologica.repository.impl;

import com.backend.clinicaOdontologica.entity.Odontologo;
import com.backend.clinicaOdontologica.repository.IDao;
import com.backend.clinicaOdontologica.repository.dbconnection.H2Connection;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    private final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;
        Odontologo odontoloRegistrado = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ODONTOLOGOS (NUMERO_MATRICULA,NOMBRE, APELLIDO) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, odontologo.getNumero_matricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());

            preparedStatement.execute();

            odontoloRegistrado = new Odontologo(odontologo.getNumero_matricula(), odontologo.getNombre(), odontologo.getApellido());

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                odontoloRegistrado.setId(resultSet.getLong("ID"));
            }

            connection.commit();

            LOGGER.info("Odontólogo registrado correctamente: " + odontoloRegistrado);
        } catch (Exception e) {
            LOGGER.error("Error al intentar registrar el odontólogo: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("Tuvimos un problema");
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error("Error al hacer rollback: " + exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();

            } catch (SQLException ex) {
                LOGGER.error("Error al cerrar la conexión: " + ex.getMessage());
            }
        }

        return odontoloRegistrado;
    }

    @Override
    public List<Odontologo> listarTodos() {
        List<Odontologo> odontologos = new ArrayList<>();
        Connection connection = null;
        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Odontologo odontologo = new Odontologo(resultSet.getLong("id"), resultSet.getInt("NUMERO_MATRICULA"), resultSet.getString("NOMBRE"), resultSet.getString("APELLIDO"));
                odontologos.add(odontologo);
            }
        } catch (Exception e) {
            LOGGER.error("Error al listar los odontólogos: " + e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                connection.close();

            } catch (SQLException ex) {
                LOGGER.error("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
        LOGGER.info("Listado de todos los odontologos: " + odontologos);
        return odontologos;
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        Odontologo odontologoBuscado = null;
        Connection connection = null;
        try {
            connection = H2Connection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS WHERE ID = ?");
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                odontologoBuscado = new Odontologo(resultSet.getLong("id"), resultSet.getInt("NUMERO_MATRICULA"), resultSet.getString("NOMBRE"), resultSet.getString("APELLIDO"));
                ;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return odontologoBuscado;
    }
}

