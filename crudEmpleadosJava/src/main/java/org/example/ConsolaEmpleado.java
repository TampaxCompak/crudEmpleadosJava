package org.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsolaEmpleado {

    private DAOEmpleado daoEmpleado;
    private Scanner scanner;

    public ConsolaEmpleado() {
        daoEmpleado = new DAOEmpleado();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("==== Gestión de Empleados ====");
            System.out.println("1. Listar todos los empleados");
            System.out.println("2. Buscar empleado por ID");
            System.out.println("3. Insertar nuevo empleado");
            System.out.println("4. Actualizar empleado");
            System.out.println("5. Eliminar empleado por ID");
            System.out.println("6. Eliminar todos los empleados");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();

            try {
                switch (opcion) {
                    case 1:
                        listarEmpleados();
                        break;
                    case 2:
                        buscarEmpleadoPorId();
                        break;
                    case 3:
                        insertarEmpleado();
                        break;
                    case 4:
                        actualizarEmpleado();
                        break;
                    case 5:
                        eliminarEmpleadoPorId();
                        break;
                    case 6:
                        eliminarTodosLosEmpleados();
                        break;
                    case 0:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (SQLException e) {
                System.err.println("Error en la operación: " + e.getMessage());
            }
        }
    }

    private void listarEmpleados() throws SQLException {
        ArrayList<DTOEmpleado> empleados = daoEmpleado.getAllEmpleados();
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados en la base de datos.");
        } else {
            for (DTOEmpleado empleado : empleados) {
                System.out.println("ID: " + empleado.getId() +
                        ", Nombre: " + empleado.getNombre() +
                        ", Edad: " + empleado.getEdad() +
                        ", Departamento ID: " + empleado.getDpto_id());
            }
        }
    }

    private void buscarEmpleadoPorId() throws SQLException {
        System.out.print("Introduce el ID del empleado: ");
        int id = scanner.nextInt();

        DTOEmpleado empleado = daoEmpleado.getEmpleadoById(id);
        if (empleado != null) {
            System.out.println("ID: " + empleado.getId() +
                    ", Nombre: " + empleado.getNombre() +
                    ", Edad: " + empleado.getEdad() +
                    ", Departamento ID: " + empleado.getDpto_id());
        } else {
            System.out.println("No se encontró un empleado con el ID " + id);
        }
    }

    private void insertarEmpleado() throws SQLException {
        scanner.nextLine();
        System.out.print("Introduce el nombre del empleado: ");
        String nombre = scanner.nextLine();
        System.out.print("Introduce la edad del empleado: ");
        int edad = scanner.nextInt();
        System.out.print("Introduce el ID del departamento: ");
        int dptoId = scanner.nextInt();

        DTOEmpleado empleado = new DTOEmpleado(0, nombre, edad, dptoId);
        daoEmpleado.addEmpleado(empleado);
        System.out.println("Empleado insertado correctamente.");
    }

    private void actualizarEmpleado() throws SQLException {
        System.out.print("Introduce el ID del empleado a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Introduce el nuevo nombre del empleado: ");
        String nombre = scanner.nextLine();
        System.out.print("Introduce la nueva edad del empleado: ");
        int edad = scanner.nextInt();
        System.out.print("Introduce el nuevo ID del departamento: ");
        int dptoId = scanner.nextInt();

        DTOEmpleado empleado = new DTOEmpleado(id, nombre, edad, dptoId);
        int filasAfectadas = daoEmpleado.updateEmpleado(empleado);
        if (filasAfectadas > 0) {
            System.out.println("Empleado actualizado correctamente.");
        } else {
            System.out.println("No se encontró un empleado con el ID " + id);
        }
    }

    private void eliminarEmpleadoPorId() throws SQLException {
        System.out.print("Introduce el ID del empleado a eliminar: ");
        int id = scanner.nextInt();

        int filasAfectadas = daoEmpleado.deleteEmpleadoById(id);
        if (filasAfectadas > 0) {
            System.out.println("Empleado eliminado correctamente.");
        } else {
            System.out.println("No se encontró un empleado con el ID " + id);
        }
    }

    private void eliminarTodosLosEmpleados() throws SQLException {
        int filasAfectadas = daoEmpleado.deleteAllEmpleados();
        System.out.println("Se eliminaron " + filasAfectadas + " empleados.");
    }

    public static void main(String[] args) {
        ConsolaEmpleado consola = new ConsolaEmpleado();
        consola.mostrarMenu();
    }
}