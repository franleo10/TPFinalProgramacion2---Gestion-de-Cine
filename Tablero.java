import java.util.HashMap;
import java.util.Map;

public class Tablero {

    // Instancia única del Tablero
    private static Tablero instancia;

    // Mapa que almacena los proyectos con el ID como clave
    private Map<Integer, Proyecto> proyectos;

    // Constructor privado para evitar instanciación directa
    private Tablero() {
        this.proyectos = new HashMap<>();
    }

    // Método estático para obtener la instancia única
    public static Tablero getInstance() {
        if (instancia == null) {
            instancia = new Tablero();
        }
        return instancia;
    }

    // Método para agregar un proyecto al tablero
    public void agregarProyecto(Proyecto proyecto) {
        proyectos.put(proyecto.getId(), proyecto);
        System.out.println("Proyecto '" + proyecto.getNombre() + "' agregado al tablero.");
    }

    // Método para eliminar un proyecto del tablero
    public boolean eliminarProyecto(int id, Administrador administrador) {
        // Verificar si el administrador está autorizado para eliminar proyectos
        if (administrador != null) {
            if (proyectos.containsKey(id)) {
                proyectos.remove(id);
                System.out.println("Proyecto con ID " + id + " eliminado.");
                return true;
            } else {
                System.out.println("No se encontró un proyecto con el ID " + id);
            }
        } else {
            System.out.println("Acceso denegado: solo un Administrador puede eliminar proyectos.");
        }
        return false;
    }

    // Método para listar todos los proyectos
    public void listarProyectos() {
        if (proyectos.isEmpty()) {
            System.out.println("No hay proyectos en el tablero.");
            return;
        }
        System.out.println("Proyectos en el tablero:");
        for (Proyecto proyecto : proyectos.values()) {
            System.out.println(proyecto);
        }
    }

    // Método para buscar un proyecto por su ID
    public Proyecto buscarProyectoPorId(int id) {
        return proyectos.get(id);
    }
}