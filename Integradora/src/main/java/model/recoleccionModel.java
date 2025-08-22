/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Osmar
 */
public class recoleccionModel {
    //Declaracion de variables
    int idRecoleccion,idUsuario,idCliente;
    String detalles;
     String fecha;
//Getters y Setters
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public int getIdRecoleccion() {
        return idRecoleccion;
    }

    public void setIdRecoleccion(int idRecoleccion) {
        this.idRecoleccion = idRecoleccion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
    //Constructor lleno
    public recoleccionModel(int idRecoleccion,String fecha,int idCliente,int idUsuario,String detalles){
       this.idRecoleccion=idRecoleccion; 
       this.fecha=fecha;
       this.detalles=detalles;
       this.idCliente=idCliente;
       this.idUsuario=idUsuario;
    }
    //Construcotr sin la llave primaria
    public recoleccionModel(String fecha,int idCliente,int idUsuario,String detalles){
       this.fecha=fecha;
       this.detalles=detalles;
       this.idCliente=idCliente;
       this.idUsuario=idUsuario;
    }
}
