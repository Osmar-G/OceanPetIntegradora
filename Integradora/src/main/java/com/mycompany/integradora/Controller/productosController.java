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
import model.productosModel;



/**
 *
 * @author Osmar
 */
public class productosController { // Clase controladora para operaciones CRUD sobre la tabla 'productos'.

    public static boolean insertarProducto(productosModel producto) { // Método para insertar un nuevo producto.
        String sql = "INSERT INTO productos(nombre_producto,descripcion,precio_unitario) VALUES (?,?,?)"; // Sentencia SQL con parámetros.
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) { // try-with-resources: abre conexión y prepara el statement; se cierran automáticamente.
            ps.setString(1, producto.getNombre());     // Parám. 1: nombre_producto.
            ps.setString(2, producto.getDescripcion()); // Parám. 2: descripcion.
            ps.setFloat(3, producto.getPrecio());       // Parám. 3: precio_unitario (usa float en el modelo).
            ps.executeUpdate();                         // Ejecuta la inserción (INSERT/UPDATE/DELETE).
            return true;                                // Retorna true si no hubo excepción.
        } catch (SQLException ex) {                     // Captura errores de SQL.
            ex.printStackTrace();                       // Traza el error (idealmente usar un logger).
            return false;                               // Indica fallo en la operación.
        }                                               // Fin del bloque try-catch.
    }                                                   // Fin de insertarProducto.

    public static List<productosModel> obtenerTodos() { // Método para listar todos los productos.
        var lista = new ArrayList<productosModel>();    // Estructura para acumular resultados.
        String sql = "SELECT * FROM productos";         // Consulta de todos los campos (puedes listar columnas explícitamente).
        try (Connection con = conexionDB.obtenerConexion(); // Abre conexión.
             Statement st = con.createStatement();          // Crea un Statement simple (sin parámetros).
             ResultSet rs = st.executeQuery(sql)) {         // Ejecuta la consulta y obtiene el ResultSet.
            while (rs.next()) {                             // Itera por cada registro del resultado.
                lista.add(new productosModel(               // Crea un productosModel por fila y lo agrega a la lista.
                        rs.getInt("id_producto"),           // Lee id_producto (INT).
                        rs.getString("nombre_producto"),    // Lee nombre_producto (VARCHAR).
                        rs.getString("descripcion"),        // Lee descripcion (VARCHAR/TEXT).
                        rs.getFloat("precio_unitario")      // Lee precio_unitario (FLOAT/DECIMAL).
                ));
            }
        } catch (SQLException ex) {                         // Manejo de excepciones SQL.
            ex.printStackTrace();                           // Traza de error.
        }
        return lista;                                       // Devuelve la lista (vacía si hubo error).
    }                                                       // Fin de obtenerTodos.

    public static List<productosModel> buscarProducto(String nombre) { // Busca productos por nombre (LIKE).
        var lista = new ArrayList<productosModel>();        // Lista de resultados.
        String sql = "SELECT * FROM productos WHERE nombre_producto LIKE ?"; // Consulta parametrizada con LIKE.
        try (Connection con = conexionDB.obtenerConexion(); // Abre conexión.
             PreparedStatement ps = con.prepareStatement(sql)) { // Prepara statement con parámetro.
            ps.setString(1, "%" + nombre + "%");            // Parám. 1: patrón de búsqueda con comodines.
            ResultSet rs = ps.executeQuery();               // Ejecuta SELECT y obtiene ResultSet.
            while (rs.next()) {                             // Recorre resultados.
                lista.add(new productosModel(               // Mapea cada fila al modelo.
                        rs.getInt("id_producto"),
                        rs.getString("nombre_producto"),
                        rs.getString("descripcion"),
                        rs.getFloat("precio_unitario")
                ));
            }
        } catch (SQLException ex) {                         // Manejo de excepción.
            ex.printStackTrace();                           // Traza de error.
        }
        return lista;                                       // Devuelve coincidencias (o lista vacía).
    }                                                       // Fin de buscarProducto.

    public static boolean eliminarProducto(int idProducto) { // Elimina un producto por su ID.
        String sql = "DELETE FROM productos WHERE id_producto = ?"; // Sentencia DELETE con parámetro.
        try (Connection con = conexionDB.obtenerConexion(); // Abre conexión.
             PreparedStatement ps = con.prepareStatement(sql)) { // Prepara statement.
            ps.setInt(1, idProducto);                       // Parám. 1: id del producto a borrar.
            ps.executeUpdate();                             // Ejecuta la eliminación.
            return true;                                    // Indica éxito si no hubo excepción.
        } catch (SQLException ex) {                         // Captura errores SQL.
            ex.printStackTrace();                           // Traza de error.
            return false;                                   // Indica fallo.
        }                                                   // Fin try-catch.
    }                                                       // Fin de eliminarProducto.

    public static boolean actualizarProducto(productosModel producto) { // Actualiza un producto existente.
        String sql = "UPDATE productos SET nombre_producto = ?, descripcion = ?, precio_unitario = ? WHERE id_producto = ?"; // SQL UPDATE.
        boolean actualizado = false;                         // Bandera para saber si hubo filas afectadas.
        try (Connection con = conexionDB.obtenerConexion();  // Abre conexión.
             PreparedStatement ps = con.prepareStatement(sql)) { // Prepara statement.
            ps.setString(1, producto.getNombre());          // Parám. 1: nuevo nombre_producto.
            ps.setString(2, producto.getDescripcion());     // Parám. 2: nueva descripcion.
            ps.setFloat(3, producto.getPrecio());           // Parám. 3: nuevo precio_unitario.
            ps.setInt(4, producto.getId());                 // Parám. 4: id del producto a actualizar.
            int filasAfectadas = ps.executeUpdate();        // Ejecuta y obtiene # de filas modificadas.
            actualizado = filasAfectadas > 0;               // true si modificó al menos una fila.
        } catch (SQLException ex) {                         // Manejo de excepciones SQL.
            ex.printStackTrace();                           // Traza de error.
        }
        return actualizado;                                  // Retorna el estado de la actualización.
    }                                                        // Fin de actualizarProducto.
}                                                            // Fin de la clase productosController.

