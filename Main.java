//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Crear usuarios
        Usuario usuario1 = new Usuario("Juan", "Usuario");
        Usuario usuario2 = new Usuario("Ana", "Usuario");
        Usuario usuario3 = new Usuario("Pedro", "Usuario");
        Usuario usuario4 = new Usuario("Gian", "Usuario");

        // Crear algunas tareas
        Tarea tarea1 = new Tarea("Tarea 1", "Descripción de la tarea 1", Prioridad.Alta, Estado.Pendiente);
        Tarea tarea2 = new Tarea("Tarea 2", "Descripción de la tarea 2", Prioridad.Media, Estado.Proceso);
        Tarea tarea3 = new Tarea("Tarea 3", "Descripción de la tarea 3", Prioridad.Baja, Estado.Finalizado);

        // Agregar usuarios a las tareas
        tarea1.agregarUsuario(usuario1);
        tarea1.agregarUsuario(usuario2);
        tarea2.agregarUsuario(usuario3);

        // Crear proyectos y agrego tareas
        Proyecto proyecto = new Proyecto("Proyecto 1");
        Proyecto proyecto2 = new Proyecto("Proyecto 2");
        proyecto.agregarTarea(1);
        proyecto.agregarTarea(2);

        Tablero tablero = new Tablero();
        tablero.agregarProyecto(proyecto);
        tablero.agregarProyecto(proyecto2);

        // Crear administrador
        Administrador admin = new Administrador("Admin", "ADMIN");

        GestorTareas gestorTareas = GestorTareas.getInstance();

        admin.agregarTareaAlProyecto(1);
        System.out.println(tablero.mostrarTareasDelProyecto(1));
 
        admin.menu();

    }

}
