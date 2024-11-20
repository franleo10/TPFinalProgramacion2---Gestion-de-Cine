import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Columna {

    private static int contador = 1;
    private int id;
    private String nombre;
    private Map<Estado, Set<Tarea>> tareasPorEstado;

    public Columna(String nombre) {
        this.id=contador++;
        this.nombre = nombre;
        tareasPorEstado=new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Map<Estado, Set<Tarea>> getTareasPorEstado() {
        return tareasPorEstado;
    }
    public void VerificarExistenciaTarea(Tarea tarea) throws TareaInexistente{ //verifica que no sea nula o ya este

        Set<Tarea> tareas = tareasPorEstado.get(tarea.getEstado());
        if (tareas != null && tareas.contains(tarea)) {
            throw new TareaInexistente("La tarea ya existe en este estado en la columna.");
        }

    }

    public void agregarTarea(Tarea tarea) {
        try {
            // Primero verificamos si la tarea ya existe
            VerificarExistenciaTarea(tarea);

            // Si no existe, la agregamos a la columna
            Set<Tarea> tareas = tareasPorEstado.computeIfAbsent(tarea.getEstado(), k -> new HashSet<>());
            tareas.add(tarea);
            System.out.println("Tarea agregada con éxito a la columna.");
        } catch (TareaInexistente e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarTarea(Tarea tarea) { ///Me elimina la tarea ya verificada con el metodo...
        try {
            // Primero verificamos si la tarea existe
            VerificarExistenciaTarea(tarea);

            // Si la tarea existe, la eliminamos
            Set<Tarea> tareas = tareasPorEstado.get(tarea.getEstado());
            tareas.remove(tarea);
            if (tareas.isEmpty()) {
                tareasPorEstado.remove(tarea.getEstado()); // Si no hay más tareas en el estado, eliminamos el estado
            }
            System.out.println("Tarea eliminada con éxito de la columna.");
        } catch (TareaInexistente e) {
            System.out.println(e.getMessage());
        }
    }


    ///Metodo para buscar en una columna, una tarea especifica que me va a servir o para eliminar un usuario de una tarea o la tarea misma.














}
