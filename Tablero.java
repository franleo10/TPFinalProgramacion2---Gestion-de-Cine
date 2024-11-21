import java.util.ArrayList;
import java.util.List;

public class Tablero {

    private List<Proyecto> proyectos;

    private static Tablero instancia;

    public Tablero() {
        this.proyectos = new ArrayList<>();
    }

    public static Tablero getInstance() {
        if (instancia == null) {
            instancia = new Tablero();
        }
        return instancia;
    }

    // Método para agregar un proyecto al tablero
    public void agregarProyecto(Proyecto proyecto) {
        proyectos.add(proyecto);
    }

    // Método para obtener un proyecto por su ID
    public Proyecto obtenerProyectoPorId(int idProyecto) {
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId() == idProyecto) {
                return proyecto;
            }
        }
        return null;
    }

    // Método para obtener todos los proyectos
    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public String mostrarProyectosActivos(){
        StringBuilder sb = new StringBuilder();

        for(Proyecto proyecto: proyectos){
            if(proyecto.activo){
                sb.append(proyecto).append("\n");
            }
        }

        return sb.toString();
    }

    public String mostrarProyectosInactivos(){
        StringBuilder sb = new StringBuilder();

        for(Proyecto proyecto: proyectos){
            if(!proyecto.activo){
                sb.append(proyecto).append("\n");
            }
        }

        return sb.toString();
    }

    public String agregarTareaAProyecto(int idProyecto, Tarea tareaNueva) {
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId() == idProyecto) {
                proyecto.agregarTareaNuevaAlProyecto(tareaNueva);
                return "Se agregó la tarea con éxito al proyecto con ID: " + idProyecto;
            }
        }
        // Si no se encontró el proyecto, lanzar un mensaje de error
        return "No se encontró el proyecto con ID: " + idProyecto;
    }


    public String mostrarTareasDelProyecto(int idProyecto){
        for(Proyecto proyecto: proyectos){
            if(proyecto.getId() == idProyecto){
                return proyecto.mostrarTareas();
            }
        }
        return "El ID del proyecto que indico no existe";
    }
}