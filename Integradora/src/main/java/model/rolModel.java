/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Osmar
 */
public class rolModel {
    //Declaracion de variables
  private int idRol;  
  private String nombre;
//Getters y Setters
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
    //Construcotr lleno
  public rolModel(int idRol, String nombre){
      this.idRol=idRol;
      this.nombre=nombre;
  }
  //Constructor sin la llave primaria
  public rolModel(String nombre){
      this.nombre=nombre;
  }
  //Devulve un String
  @Override
public String toString() {
    return this.nombre;
}

}
