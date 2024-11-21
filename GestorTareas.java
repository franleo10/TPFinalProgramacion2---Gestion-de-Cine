import java.util.HashMap;
import java.util.Map;

public class GestorTareas {
    
    // Instancia única del gestor
    private static GestorTareas instancia;
    
    // Mapa que almacena las tareas con el ID como clave
    private Map<Integer, Tarea> tareas;

    // Constructor privado para evitar instanciación directa
    private GestorTareas() {
        this.tareas = new HashMap<>();
    }

    // Método estático para obtener la instancia única
    public static GestorTareas getInstance() {
        if (instancia == null) {
            instancia = new GestorTareas();
        }
        return instancia;
    }

    // Agregar una tarea al sistema
    public boolean agregarTarea(Tarea tarea) {
        if (tarea == null) {
            System.out.println("Error: La tarea no puede ser nula.");
            return false;
        }

        // Verificar si la tarea ya está en el sistema por su ID
        if (tareas.containsKey(tarea.getId())) {
            System.out.println("La tarea con ID " + tarea.getId() + " ya está en el sistema.");
            return false;
        }

        // Agregar tarea al mapa
        tareas.put(tarea.getId(), tarea);
        System.out.println("Tarea con ID " + tarea.getId() + " agregada al sistema.");
        return true;
    }

    // Eliminar una tarea del sistema por su ID
    public boolean eliminarTarea(int id) {
        if (!tareas.containsKey(id)) {
            System.out.println("La tarea con ID " + id + " no está registrada en el sistema.");
            return false;
        }

        // Eliminar tarea del mapa
        tareas.remove(id);
        System.out.println("Tarea con ID " + id + " eliminada del sistema.");
        return true;
    }

    // Buscar una tarea por ID
    public Tarea buscarTareaPorId(int id) {
        if (tareas.containsKey(id)) {
            return tareas.get(id);
        }
        System.out.println("Tarea con ID " + id + " no encontrada.");
        return null;
    }

    // Listar todas las tareas en el sistema
    public void listarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas en el sistema.");
            return;
        }

        System.out.println("Tareas registradas en el sistema:");
        for (Tarea tarea : tareas.values()) {
            System.out.println(tarea);
        }
    }





    //////////////////////////////////////////////////////////////////////////////////////////

    public boolean modificarTarea(int id, String nuevoTitulo, String nuevaDescripcion, Prioridad nuevaPrioridad, Estado nuevoEstado) {
        Tarea tarea = buscarTareaPorId(id);
        if (tarea == null) {
            System.out.println("Tarea con ID " + id + " no encontrada.");
            return false;
        }
        tarea.setTitulo(nuevoTitulo);
        tarea.setDescripcion(nuevaDescripcion);
        tarea.setPrioridad(nuevaPrioridad);
        tarea.setEstado(nuevoEstado);
        System.out.println("Tarea modificada exitosamente.");
        return true;
    }

    
    public void mostrarTodosLosElementos() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas en el sistema.");
            return;
        }
    
        System.out.println("Elementos en el mapa (ID -> Tarea):");
        for (Map.Entry<Integer, Tarea> entry : tareas.entrySet()) {
            System.out.println("ID: " + entry.getKey() + " -> " + entry.getValue());
        }
    }

}