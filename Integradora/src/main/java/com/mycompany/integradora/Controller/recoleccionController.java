/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integradora.Controller;

import com.mycompany.integradora.Conexion.conexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.recoleccionModel;



/**
 *
 * @author Osmar
 */
/**
 * Clase controladora para manejar las operaciones CRUD 
 * (Crear, Leer, Actualizar y Eliminar) de la tabla 'recolecciones'.
 *
 * Cada método se conecta a la base de datos a través de la clase conexionDB
 * y trabaja con objetos del modelo recoleccionModel.
 */
public class recoleccionController {

    /**
     * Inserta una nueva recolección en la base de datos.
     *
     * @param recolec Objeto de tipo recoleccionModel con los datos a insertar.
     * @return true si la inserción fue exitosa, false si ocurrió un error.
     */
    public static boolean insertarRecoleccion(recoleccionModel recolec) {
        String sql = "INSERT INTO recolecciones(fecha_recoleccion, id_cliente, id_usuario, detalles) VALUES (?,?,?,?)";
        try (Connection con = conexionDB.obtenerConexion(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, recolec.getFecha());       // Fecha de la recolección.
            ps.setInt(2, recolec.getIdCliente());      // ID del cliente asociado.
            ps.setInt(3, recolec.getIdUsuario());      // ID del usuario responsable.
            ps.setString(4, recolec.getDetalles());    // Detalles adicionales.

            ps.executeUpdate();                        // Ejecuta la sentencia SQL.
            return true;                               // Retorna true si todo salió bien.
        } catch (SQLException ex) {
            ex.printStackTrace();                      // Imprime error en consola.
            return false;                              // Retorna false en caso de error.
        }
    }

    /**
     * Obtiene todas las recolecciones registradas en la tabla.
     *
     * @return Una lista de objetos recoleccionModel con todos los registros.
     */
    public static List<recoleccionModel> obtenerTodos() {
        var lista = new ArrayList<recoleccionModel>(); // Lista para almacenar resultados.
        String sql = "SELECT * FROM recolecciones";

        try (Connection con = conexionDB.obtenerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) { // Recorre cada fila del resultado.
                lista.add(new recoleccionModel(
                        rs.getInt("id_recoleccion"),      // ID de la recolección.
                        rs.getString("fecha_recoleccion"),// Fecha.
                        rs.getInt("id_cliente"),          // Cliente asociado.
                        rs.getInt("id_usuario"),          // Usuario asociado.
                        rs.getString("detalles")          // Detalles.
                ));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(); // Traza de error en consola.
        }
        return lista; // Retorna la lista (puede estar vacía si no hay registros).
    }

    /**
     * Busca recolecciones por fecha usando la cláusula LIKE.
     *
     * @param fecha Texto a buscar en el campo fecha_recoleccion.
     * @return Una lista con las recolecciones que coinciden.
     */
    public static List<recoleccionModel> buscarRecoleccion(String fecha) {
        var lista = new ArrayList<recoleccionModel>();
        String sql = "SELECT * FROM recolecciones WHERE fecha_recoleccion LIKE ?";

        try (Connection con = conexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + fecha + "%"); // Patrón de búsqueda (ej: %2025-08%).
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new recoleccionModel(
                        rs.getInt("id_recoleccion"),
                        rs.getString("fecha_recoleccion"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_usuario"),
                        rs.getString("detalles")
                ));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    /**
     * Elimina una recolección de la base de datos por su ID.
     *
     * @param idRecolec ID de la recolección a eliminar.
     * @return true si la eliminación fue exitosa, false si ocurrió un error.
     */
    public static boolean eliminarRecoleccion(int idRecolec) {
        String sql = "DELETE FROM recolecciones WHERE id_recoleccion = ?";

        try (Connection con = conexionDB.obtenerConexion(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idRecolec); // Asigna el ID al parámetro de la consulta.
            ps.executeUpdate();      // Ejecuta DELETE.
            return true;             // Retorna true si no hubo error.

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;            // Retorna false si hubo excepción.
        }
    }

    /**
     * Actualiza los datos de una recolección existente en la base de datos.
     *
     * @param recolec Objeto con los nuevos datos de la recolección.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public static boolean actualizarRecoleccion(recoleccionModel recolec) {
        String sql = "UPDATE recolecciones SET fecha_recoleccion = ?, id_cliente = ?, id_usuario = ?, detalles = ? WHERE id_recoleccion = ?";
        boolean actualizado = false;

        try (Connection con = conexionDB.obtenerConexion(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, recolec.getFecha());           // Nueva fecha.
            ps.setInt(2, recolec.getIdCliente());          // Nuevo cliente asociado.
            ps.setInt(3, recolec.getIdUsuario());          // Nuevo usuario asociado.
            ps.setString(4, recolec.getDetalles());        // Nuevos detalles.
            ps.setInt(5, recolec.getIdRecoleccion());      // ID de la recolección a actualizar.

            int filasAfectadas = ps.executeUpdate();       // Ejecuta el UPDATE.
            actualizado = filasAfectadas > 0;              // true si al menos 1 fila fue modificada.

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return actualizado; // Retorna el estado de la operación.
    }
}

       

