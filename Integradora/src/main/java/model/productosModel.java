/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Osmar
 */
public class productosModel {
    //Declaracion de variables 
    private int id;
    private String nombre;
    private String descripcion;
    private float precio;
    //Getters y Setters de todas las variables
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
      
    }
    //Constructor lleno
    public  productosModel(int id,String nombre,String descripcion,float precio){
          this.id=id;
          this.nombre=nombre;
          this.descripcion=descripcion;
          this.precio=precio;
          }
    //Coonstructor sin la llave primaria
    public  productosModel(String nombre,String descripcion,float precio){
          this.nombre=nombre;
          this.descripcion=descripcion;
          this.precio=precio;
          }
    //Meetodo que devuelve el nombre en valor String
    @Override
public String toString() {
    return this.nombre;
}

}
