package org.example;

public class DTOEmpleado {
    private int id;
    private String nombre;
    private int edad;
    private int dpto_id;

    public DTOEmpleado() {
        super();
    }

    public DTOEmpleado(int id, String nombre, int edad, int dpto_id) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.dpto_id = dpto_id;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getDpto_id() {
        return dpto_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDpto_id(int dpto_id) {
        this.dpto_id = dpto_id;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", dpto_id=" + dpto_id +
                '}';
    }
}
