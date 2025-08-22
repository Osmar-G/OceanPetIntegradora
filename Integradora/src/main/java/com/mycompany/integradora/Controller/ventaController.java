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
import model.ventaModel;


/**
 *
 * @author Osmar
 */
/**
 * Controlador para la tabla "ventas".
 * Permite realizar operaciones básicas: crear, leer, actualizar y eliminar ventas.
 */
public class ventaController {

    /**
     * Inserta una nueva venta en la base de datos.
     *
     * @param venta Objeto ventaModel con los datos de la venta.
     * @return true si la inserción fue exitosa, false si hubo un error.
     */
    public static boolean insertarVenta(ventaModel venta) {
        String sql = "INSERT INTO ventas(fecha_venta,id_usuario,id_cliente,cantidad,id_producto,precio,total) VALUES (?,?,?,?,?,?,?)";
        try (Connection con = conexionDB.obtenerConexion(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, venta.getFecha());
            ps.setInt(2, venta.getIdUsuario());
            ps.setInt(3, venta.getIdCliente());
            ps.setInt(4, venta.getCantidad());
            ps.setInt(5, venta.getIdProducto());
            ps.setFloat(6, venta.getPrecio());
            ps.setFloat(7, venta.getTotal());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene todas las ventas registradas en la base de datos.
     *
     * @return Lista de todas las ventas.
     */
    public static List<ventaModel> obtenerTodos() {
        var lista = new ArrayList<ventaModel>();
        String sql = "SELECT * FROM ventas";
        try (Connection con = conexionDB.obtenerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new ventaModel(
                        rs.getInt("id_venta"),
                        rs.getString("fecha_venta"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_cliente"),
                        rs.getInt("cantidad"),
                        rs.getInt("id_producto"),
                        rs.getFloat("precio"),
                        rs.getFloat("total")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    /**
     * Busca ventas por fecha.
     *
     * @param fecha Fecha o parte de la fecha de la venta a buscar.
     * @return Lista de ventas que coinciden con la fecha.
     */
    public static List<ventaModel> buscarVenta(String fecha) {
        var lista = new ArrayList<ventaModel>();
        String sql = "SELECT * FROM ventas WHERE fecha_venta LIKE ?";
        try (Connection con = conexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + fecha + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new ventaModel(
                        rs.getInt("id_venta"),
                        rs.getString("fecha_venta"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_cliente"),
                        rs.getInt("cantidad"),
                        rs.getInt("id_producto"),
                        rs.getFloat("precio"),
                        rs.getFloat("total")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    /**
     * Elimina una venta por su ID.
     *
     * @param idVenta ID de la venta a eliminar.
     * @return true si se eliminó correctamente, false si hubo un error.
     */
    public static boolean eliminarVenta(int idVenta) {
        String sql = "DELETE FROM ventas WHERE id_venta = ?";
        try (Connection con = conexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idVenta);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Actualiza los datos de una venta existente.
     *
     * @param venta Objeto ventaModel con los nuevos datos y el ID de la venta.
     * @return true si se actualizó correctamente, false si hubo un error.
     */
    public static boolean actualizarVenta(ventaModel venta) {
        String sql = "UPDATE ventas SET fecha_venta = ?, id_usuario = ?, id_cliente = ?, cantidad = ?, id_producto = ?, precio = ?, total = ? WHERE id_venta = ?";
        boolean actualizado = false;
        try (Connection con = conexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, venta.getFecha());
            ps.setInt(2, venta.getIdUsuario());
            ps.setInt(3, venta.getIdCliente());
            ps.setInt(4, venta.getCantidad());
            ps.setInt(5, venta.getIdProducto());
            ps.setFloat(6, venta.getPrecio());
            ps.setFloat(7, venta.getTotal());
            ps.setInt(8, venta.getIdVenta());

            int filasAfectadas = ps.executeUpdate();
            actualizado = filasAfectadas > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return actualizado;
    }
}
