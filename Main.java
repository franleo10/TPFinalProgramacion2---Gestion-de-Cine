//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Crear usuarios
        Usuario usuario1 = new Usuario("Juan", "Usuario", "asbfjkhsa2");
        Usuario usuario2 = new Usuario("Ana", "Usuario", "aksdnfajkhs");
        Usuario usuario3 = new Usuario("Pedro", "Usuario", "sadkfjn");
        Usuario usuario4 = new Usuario("Gian", "Usuario", "asdnfkajsn");

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
        proyecto.agregarTareaNuevaAlProyecto(tarea1);
        proyecto.agregarTareaNuevaAlProyecto(tarea2);

        // Crear administrador
        Administrador admin = new Administrador("Admin", "ADMIN", "kasjnf21");

        GestorProyectos gestorProyectos = GestorProyectos.getInstance();

        GestorUsuarios gestorUsuarios = GestorUsuarios.getInstance();


      
       admin.menuUsuariosYTareas();

        gestorUsuarios.listarUsuarios();

        gestorProyectos.mostrarTareasDelProyecto(1);

    }

}
