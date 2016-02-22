/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.primefaces.model.StreamedContent;

/**
 *
 * @author inftel08
 */
public class ListaCiudadesStreamed {
    
    private String descripcion;
    private String nombre;
    private StreamedContent imagen;

    public ListaCiudadesStreamed(String descripcion, String nombre, StreamedContent imagen) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public StreamedContent getImagen() {
        return imagen;
    }

    public void setImagen(StreamedContent imagen) {
        this.imagen = imagen;
    }
    
    
    
}
