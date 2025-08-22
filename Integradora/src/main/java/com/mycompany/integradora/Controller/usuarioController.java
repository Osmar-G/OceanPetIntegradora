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

import model.usuarioModel;


/**
 *
 * @author Osmar
 */
/**
 * Controlador para la tabla "usuarios".
 * Permite realizar operaciones básicas: crear, leer, actualizar, eliminar usuarios
 * y validar el login.
 */
public class usuarioController {

    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param usuarios Objeto usuarioModel con los datos del usuario.
     * @return true si la inserción fue exitosa, false si hubo un error.
     */
    public static boolean insertarCliente(usuarioModel usuarios) {
        String sql = "INSERT INTO usuarios(nombre,apellidoP,apellidoM,telefono,contraseña,id_rol,direccion,usuario) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection con = conexionDB.obtenerConexion(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuarios.getNombre());
            ps.setString(2, usuarios.getApellidoP());
            ps.setString(3, usuarios.getApellidoM());
            ps.setString(4, usuarios.getTelefono());
            ps.setString(5, usuarios.getContraseña());
            ps.setInt(6, usuarios.getIdRol());
            ps.setString(7, usuarios.getDireccion());
            ps.setString(8, usuarios.getUsuario());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene todos los usuarios registrados.
     *
     * @return Lista de todos los usuarios en la base de datos.
     */
    public static List<usuarioModel> obtenerTodos() {
        var lista = new ArrayList<usuarioModel>();
        String sql = "SELECT * FROM usuarios";
        try (Connection con = conexionDB.obtenerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new usuarioModel(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellidoP"),
                        rs.getString("apellidoM"),
                        rs.getString("telefono"),
                        rs.getString("contraseña"),
                        rs.getInt("id_rol"),
                        rs.getString("direccion"),
                        rs.getString("usuario")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    /**
     * Busca usuarios por su nombre.
     *
     * @param nombre Nombre o parte del nombre a buscar.
     * @return Lista de usuarios que coinciden con el nombre.
     */
    public static List<usuarioModel> buscarUsuarioNombre(String nombre) {
        var lista = new ArrayList<usuarioModel>();
        String sql = "SELECT * FROM usuarios WHERE nombre LIKE ?";
        try (Connection con = conexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new usuarioModel(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellidoP"),
                        rs.getString("apellidoM"),
                        rs.getString("telefono"),
                        rs.getString("contraseña"),
                        rs.getInt("id_rol"),
                        rs.getString("direccion"),
                        rs.getString("usuario")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    /**
     * Elimina un usuario de la base de datos por su ID.
     *
     * @param idUsuario ID del usuario a eliminar.
     * @return true si se eliminó correctamente, false si hubo un error.
     */
    public static boolean eliminarUsuario(int idUsuario) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        try (Connection con = conexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param usuario Objeto usuarioModel con los nuevos datos y el ID del usuario.
     * @return true si se actualizó correctamente, false si hubo un error.
     */
    public static boolean actualizarUsuario(usuarioModel usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, apellidoP = ?, apellidoM = ?, telefono = ?, contraseña = ?, id_rol = ?, direccion = ?, usuario = ? WHERE id_usuario = ?";
        boolean actualizado = false;
        try (Connection con = conexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidoP());
            ps.setString(3, usuario.getApellidoM());
            ps.setString(4, usuario.getTelefono());
            ps.setString(5, usuario.getContraseña());
            ps.setInt(6, usuario.getIdRol());
            ps.setString(7, usuario.getDireccion());
            ps.setString(8, usuario.getUsuario());
            ps.setInt(9, usuario.getIdUsuario());

            int filasAfectadas = ps.executeUpdate();
            actualizado = filasAfectadas > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return actualizado;
    }

    /**
     * Valida el login de un usuario comparando usuario y contraseña.
     *
     * @param usuario Nombre de usuario.
     * @param password Contraseña del usuario.
     * @return true si las credenciales coinciden con la base de datos, false en caso contrario.
     */
    public boolean login(String usuario, String password) {
        String sql = "SELECT * FROM usuarios WHERE usuario=? AND contraseña=?";
        try (Connection con = conexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // true si encontró coincidencia
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
