/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Osmar
 */
public class usuarioModel {
    //declaracion de variables
    private int idUsuario,idRol;
    private String nombre,apellidoP,apellidoM,telefono,contraseña,usuario,direccion;
    //Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    //Constructor lleno
  public usuarioModel(int idUsuario,String nombre,String apellidoP,String apellidoM,String telefono,String contraseña,int idRol,String direccion,String usuario){
        this.idUsuario=idUsuario;
        this.nombre=nombre;
        this.apellidoP=apellidoP;
        this.apellidoM=apellidoM;
        this.telefono=telefono;
        this.contraseña=contraseña;
        this.idRol=idRol;
        this.direccion=direccion;
        this.usuario=usuario;
    }
  //Construcotr sin la llave primaria
   public usuarioModel(String nombre,String apellidoP,String apellidoM,String telefono,String contraseña,int idRol,String direccion,String usuario){
        this.nombre=nombre;
        this.apellidoP=apellidoP;
        this.apellidoM=apellidoM;
        this.telefono=telefono;
        this.contraseña=contraseña;
        this.idRol=idRol;
        this.direccion=direccion;
        this.usuario=usuario;
    }
   //Devuelve un String
   @Override
public String toString() {
    return this.nombre;
}

}
