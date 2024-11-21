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

        // Agregar usuarios a las tareas
        tarea1.agregarUsuario(usuario1);
        tarea1.agregarUsuario(usuario2);
        tarea2.agregarUsuario(usuario3);


        // Crear columnas y agregar tareas a ellas
        Proyecto proyecto = new Proyecto("Proyecto 1");
        proyecto.agregarTarea(1);
        proyecto.agregarTarea(2);

        Tablero tablero = new Tablero();
        tablero.agregarProyecto(proyecto);


        // Crear administrador
        Administrador admin = new Administrador("Admin", "ADMIN");

        admin.menu();


    }



}
