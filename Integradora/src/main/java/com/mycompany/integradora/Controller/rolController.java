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
import model.rolModel;

/**
 *
 * @author Osmar
 */
public class rolController {               // Declara la clase pública rolController.

    public static boolean insertarRol(rolModel rol) {                  // Método estático: inserta un nuevo rol; retorna éxito/fallo.
        String sql = "INSERT INTO roles (nombre_rol) VALUES (?)";      // Sentencia SQL parametrizada con un placeholder (?).
        try (Connection con = conexionDB.obtenerConexion();            // Abre conexión a BD (try-with-resources la cierra automáticamente).
             PreparedStatement ps = con.prepareStatement(sql)) {       // Prepara el statement para la sentencia SQL dada.

            ps.setString(1, rol.getNombre());                          // Sustituye el parámetro 1 (?) con el nombre del rol desde el modelo.
            ps.executeUpdate();                                         // Ejecuta INSERT; devuelve # filas afectadas (no se usa aquí).
            return true;                                                // Si no hubo excepción, la inserción se considera exitosa.

        } catch (SQLException ex) {                                     // Captura cualquier error de SQL (conexión, sintaxis, etc.).
            ex.printStackTrace();                                       // Imprime la traza (recomendado usar logger en prod).
            return false;                                               // Indica que la operación falló.
        }                                                               // Fin del try-with-resources (cierre automático de recursos).
    }                                                                   // Fin de insertarRol.

    public static List<rolModel> obtenerTodos() {                       // Método estático: obtiene todos los roles como una lista.
        var lista = new ArrayList<rolModel>();                          // Crea lista vacía donde se cargarán los resultados.
        String sql = "SELECT * FROM roles";                             // Consulta para traer todas las filas y columnas de 'roles'.

        try (Connection con = conexionDB.obtenerConexion();             // Abre conexión a la BD.
             Statement st = con.createStatement();                      // Crea un Statement simple (no parametrizado).
             ResultSet rs = st.executeQuery(sql)) {                     // Ejecuta SELECT y obtiene el cursor de resultados (ResultSet).

            while (rs.next()) {                                         // Itera fila por fila del resultado.
                lista.add(new rolModel(                                 // Construye un rolModel con los valores de la fila actual…
                        rs.getInt("id_rol"),                             //   …columna id_rol (INT).
                        rs.getString("nombre_rol")                       //   …columna nombre_rol (VARCHAR).
                ));                                                     // Agrega el objeto a la lista.
            }                                                           // Fin del while.

        } catch (SQLException ex) {                                     // Captura errores durante la consulta/lectura.
            ex.printStackTrace();                                       // Imprime la traza del error.
        }                                                               // Fin del try-with-resources.

        return lista;                                                   // Devuelve la lista (vacía si no hubo resultados o hubo error).
    }                                                                   // Fin de obtenerTodos.

    public static boolean eliminarRol(int idRol) {                      // Método estático: elimina un rol por su ID; retorna éxito/fallo.
        String sql = "DELETE FROM roles WHERE id_rol = ?";              // Sentencia DELETE con parámetro para el ID.

        try (Connection con = conexionDB.obtenerConexion();             // Abre conexión a la BD.
             PreparedStatement ps = con.prepareStatement(sql)) {        // Prepara el statement para la sentencia DELETE.

            ps.setInt(1, idRol);                                        // Asigna el valor del ID al parámetro 1 (?).
            ps.executeUpdate();                                         // Ejecuta el DELETE en la base de datos.
            return true;                                                // Si no hubo excepción, se asume borrado exitoso.

        } catch (SQLException ex) {                                     // Captura errores de SQL.
            ex.printStackTrace();                                       // Imprime la traza.
            return false;                                               // Indica que la operación falló.
        }                                                               // Fin del try-with-resources.
    }                                                                   // Fin de eliminarRol.
}                                                                       // Fin de la clase rolController.
