/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Osmar
 */
public class clientesModel {
    //Declaracion de variables  de tipo Int y String
  private int idCliente;
  private String nombre,apellidoP,apellidoM,telefono;
//Getters y Setters de cada variable
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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
    //Constructor con todos los datos
   public clientesModel(int idCliente,String nombre,String apellidoP,String apellidoM,String telefono){
       this.idCliente=idCliente;
       this.nombre=nombre;
       this.apellidoP = apellidoP;
       this.apellidoM = apellidoM;
       this.telefono = telefono;
   }
   //Constructor sin la variable idCliente que es qla que representa la llave primaria
    public clientesModel(String nombre,String apellidoP,String apellidoM,String telefono){
       this.nombre=nombre;
       this.apellidoP = apellidoP;
       this.apellidoM = apellidoM;
       this.telefono = telefono;
   }
    //Devuelve el nombre como un String
    @Override
public String toString() {
    return this.nombre;
}

}
