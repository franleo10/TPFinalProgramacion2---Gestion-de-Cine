import java.util.HashMap;
import java.util.Map;

public class GestorProyectos {

    // Instancia única del gestor
    private static GestorProyectos instancia;

    // Mapa que almacena los proyectos con el ID como clave
    private Map<Integer, Proyecto> proyectos;

    // Constructor privado para evitar instanciación directa
    private GestorProyectos() {
        this.proyectos = new HashMap<>();
    }

    // Método estático para obtener la instancia única
    public static GestorProyectos getInstance() {
        if (instancia == null) {
            instancia = new GestorProyectos();
        }
        return instancia;
    }

    // Eliminar un proyecto del sistema por su ID
    public boolean eliminarProyecto(int id) {
        if (!proyectos.containsKey(id)) {
            System.out.println("El proyecto con ID " + id + " no está registrado en el sistema.");
            return false;
        }

        // Eliminar proyecto del mapa
        proyectos.remove(id);
        System.out.println("Proyecto con ID " + id + " eliminado del sistema.");
        return true;
    }

    // Buscar un proyecto por ID
    public Proyecto buscarProyectoPorId(int id) {
        if (proyectos.containsKey(id)) {
            return proyectos.get(id);
        }
        System.out.println("Proyecto con ID " + id + " no encontrado.");
        return null;
    }

    // Listar todos los proyectos en el sistema
    public void listarProyectos() {
        if (proyectos.isEmpty()) {
            System.out.println("No hay proyectos en el sistema.");
            return;
        }

        System.out.println("Proyectos registrados en el sistema:");
        for (Proyecto proyecto : proyectos.values()) {
            System.out.println(proyecto);
        }
    }

    // Método para agregar tarea a un proyecto
    public boolean agregarTareaAProyecto(int idProyecto, int idTarea) {
        Proyecto proyecto = buscarProyectoPorId(idProyecto);
        if (proyecto == null) {
            return false;
        }

        // Agregar tarea al proyecto
        proyecto.agregarTarea(idTarea);
        return true;
    }

    // Método para eliminar tarea de un proyecto
    public boolean eliminarTareaDeProyecto(int idProyecto, int idTarea) {
        Proyecto proyecto = buscarProyectoPorId(idProyecto);
        if (proyecto == null) {
            return false;
        }

        // Eliminar tarea del proyecto
        proyecto.eliminarTarea(idTarea);
        return true;
    }

    // Modificar información del proyecto
    public boolean modificarProyecto(int id, String nuevoNombre) {
        Proyecto proyecto = buscarProyectoPorId(id);
        if (proyecto == null) {
            System.out.println("Proyecto con ID " + id + " no encontrado.");
            return false;
        }

        proyecto.setNombre(nuevoNombre);
        System.out.println("Proyecto modificado exitosamente.");
        return true;
    }
}
