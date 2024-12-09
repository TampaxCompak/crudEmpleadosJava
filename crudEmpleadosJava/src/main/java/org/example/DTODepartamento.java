package org.example;

public class DTODepartamento {
    private int id;
    private String nombre;

    public DTODepartamento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

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

    @Override
    public String toString() {
        return "DTODepartamento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}