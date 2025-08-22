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
import model.clientesModel;



/**
 *
 * @author Osmar
 */
/**
 * Clase controladora para manejar las operaciones CRUD 
 * (Crear, Leer, Actualizar y Eliminar) de la tabla 'clientes'.
 * 
 * Utiliza la clase conexionDB para conectarse a la base de datos
 * y el modelo clientesModel para mapear registros.
 */
public class clientesController {
    /**
     * Inserta un nuevo cliente en la base de datos.
     *
     * @param clientes Objeto de tipo clientesModel con los datos del cliente.
     * @return true si la inserción fue exitosa, false si ocurrió un error.
     */
     public static boolean insertarCliente(clientesModel clientes) {
        String sql = "INSERT INTO clientes(nombre,apellidoP,apellidoM,telefono) VALUES (?,?,?,?)";
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, clientes.getNombre());
            ps.setString(2,clientes.getApellidoP());
            ps.setString(3,clientes.getApellidoM());
            ps.setString(4,clientes.getTelefono());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;

        }

    }
     /**
     * Obtiene todos los registros de clientes en la tabla.
     *
     * @return Una lista con todos los clientes almacenados.
     */
     public static List<clientesModel> obtenerTodos() {
        var lista = new ArrayList<clientesModel>();
        String sql = "SELECT * FROM clientes";
    try(Connection con = conexionDB.obtenerConexion();
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql)){
    while (rs.next()) {
            lista.add(new clientesModel(rs.getInt("id_cliente"), rs.getString("nombre"),rs.getString("apellidoP"),rs.getString("apellidoM"),rs.getString("telefono")));
        }
    }
    catch(SQLException ex){
        
    ex.printStackTrace();
    }
    return lista ;
}
      /**
     * Busca clientes por nombre usando la cláusula LIKE.
     *
     * @param nombre Nombre (o parte del nombre) a buscar.
     * @return Una lista de clientes que coinciden con el criterio.
     */
      public static List<clientesModel> buscarCliente(String nombre) {
        var lista = new ArrayList<clientesModel>();
        String sql = "SELECT * FROM clientes WHERE nombre LIKE ?";
    try(Connection con = conexionDB.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,"%"+nombre+"%");
            ResultSet rs = ps.executeQuery();
    while (rs.next()) {
            lista.add(new clientesModel(rs.getInt("id_cliente"), rs.getString("nombre"),rs.getString("apellidoP"),rs.getString("apellidoM"),rs.getString("telefono")));
        }
    }
    catch(SQLException ex){
        
    ex.printStackTrace();
    }
    return lista ;
}
       /**
     * Elimina un cliente de la base de datos por su ID.
     *
     * @param idCliente ID del cliente a eliminar.
     * @return true si la eliminación fue exitosa, false en caso de error.
     */
       public static boolean eliminarCliente(int idCliente) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
           
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;

        }

    }
       /**
     * Actualiza los datos de un cliente existente en la base de datos.
     *
     * @param cliente Objeto clientesModel con los nuevos valores.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
       public static boolean actualizarCliente(clientesModel cliente) {
   boolean actualizado=false;
           String sql = "UPDATE clientes SET nombre = ?, apellidoP = ?, apellidoM = ?, telefono = ? WHERE id_cliente = ?";
    try (Connection con = conexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, cliente.getNombre());
        ps.setString(2, cliente.getApellidoP());
        ps.setString(3, cliente.getApellidoM());
        ps.setString(4, cliente.getTelefono());
        ps.setInt(5, cliente.getIdCliente());  // Asegúrate de tener este método en tu modelo
        int filasAfectadas=ps.executeUpdate();
       actualizado=filasAfectadas>0;
    } catch (SQLException ex) {
        ex.printStackTrace();
       
    }
    return actualizado;
    }
       
       
       
       
}
