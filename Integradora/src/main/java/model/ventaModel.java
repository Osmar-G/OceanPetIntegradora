/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Osmar
 */
public class ventaModel {
    //Declaracion de variables
    int idVenta,idCliente,idUsuario,idProducto,cantidad;
    String fecha;
    Float total,precio;
//Getters y Setters
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
    //Constructor lleno
     public ventaModel(int idVenta,String fecha,int idUsuario,int idCliente,int cantidad,int idProducto,float precio,float total){
        this.idVenta=idVenta;
        this.idUsuario=idUsuario;
        this.idCliente=idCliente;
        this.fecha=fecha;
        this.cantidad=cantidad;
        this.idProducto=idProducto;
        this.precio=precio;
        this.total=total;
    }
     //Constructor sin llave primaria
    public ventaModel(String fecha,int idUsuario,int idCliente,int cantidad,int idProducto,float precio,float total){
        this.idUsuario=idUsuario;
        this.idCliente=idCliente;
        this.fecha=fecha;
        this.cantidad=cantidad;
        this.idProducto=idProducto;
        this.precio=precio;
        this.total=total;
    }
   
}
